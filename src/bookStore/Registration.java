package bookStore;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class Registration {
	Registration() {
		JFrame j = new JFrame("Bookstore Inventory Management System");

		JLabel lHead, lname, lpsw, lUser, lemail;
		JTextField tfuser, tfname, tfemail;
		JPasswordField tfpsw, tfcpass;
		JButton btnReg, btnBack;
		
		lHead = new JLabel("Registration Form");
		j.add(lHead);
		lHead.setBounds(120, 20, 300, 100);
		lHead.setForeground(Color.blue);
		lHead.setFont(lHead.getFont().deriveFont(20.0f));

		lname = new JLabel("Name : ");
		j.add(lname);
		lname.setBounds(100, 100, 100, 30);

		lUser = new JLabel("Username : ");
		j.add(lUser);
		lUser.setBounds(100, 140, 100, 30);

		lpsw = new JLabel("Password :");
		j.add(lpsw);
		lpsw.setBounds(100, 220, 100, 30);

		lpsw = new JLabel("Confirm Password:");
		j.add(lpsw);
		lpsw.setBounds(100, 260, 200, 30);

		lemail = new JLabel("Email : ");
		j.add(lemail);
		lemail.setBounds(100, 180, 100, 30);

		// text fields

		tfname = new JTextField(30);
		j.add(tfname);
		tfname.setBounds(230, 110, 160, 20);

		tfuser = new JTextField(10);
		j.add(tfuser);
		tfuser.setBounds(230, 150, 160, 20);

		tfemail = new JTextField(30);
		j.add(tfemail);
		tfemail.setBounds(230, 185, 160, 20);

		tfpsw = new JPasswordField(30);
		j.add(tfpsw);
		tfpsw.setBounds(230, 225, 160, 20);

		tfcpass = new JPasswordField(30);
		j.add(tfcpass);
		tfcpass.setBounds(230, 265, 160, 20);
		

		btnReg = new JButton("SignUp");
		j.add(btnReg);
		btnReg.setBounds(200, 340, 150, 30);
		btnReg.setBackground(Color.green);

		btnBack = new JButton("Back");
		j.add(btnBack);
		btnBack.setBounds(10, 10, 70, 30);
		btnBack.setBackground(Color.yellow);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				j.dispose();
			}
		});


		// save action
		btnReg.addActionListener(new ActionListener() {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {


			String name = tfname.getText();
			String user = tfuser.getText();
			String psw = tfpsw.getText();
			String email = tfemail.getText();
			
			if (tfname.getText().isEmpty() || tfuser.getText().isEmpty() || tfpsw.getText().isEmpty() || tfcpass.getText().isEmpty() || tfemail.getText().isEmpty()) {
				JOptionPane.showMessageDialog(j, "Input all fields");
			}
			else if (!tfpsw.getText().equals(tfcpass.getText())) {
				JOptionPane.showMessageDialog(j, "Passwords doesn't match");
				tfpsw.setText("");
				tfcpass.setText("");
			}
			else {
				int value = userSignup(name,user,psw,email);

				if (value == 1){
				JOptionPane.showMessageDialog(j, "You Have Successfully Signed Up!");
				new Login();
				j.dispose();
				
				}else{
				JOptionPane.showMessageDialog(j, "Username Already Exist!");
				}
			}		
		}
		});

		j.setLayout(null);
		j.setSize(600, 600);
		j.setVisible(true);
		j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		}
	
	DbConnection db = new DbConnection();
		public int userSignup(String name, String user,String psw, String email) {
			String query = "insert into accounts values('" + name + "','" + user + "','" + email + "','"
							+ psw + "')";
		

		try {
			int result = db.connection().executeUpdate(query);
			
			if(result>0)  {
	             return 1;
	         }
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return 0;

	}

}
