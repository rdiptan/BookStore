package bookStore;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Home {
	public static void main(String[] args) {
		new Login();

	}
}

class Login {
	DbConnection db = new DbConnection();

	Login() {
		JFrame f = new JFrame("Bookstore Inventory Management System");
		JLabel lUser, lpsw, lHead, lneed;
		JTextField tfUser;
		JPasswordField tfpsw;
		JButton btnLogin, btnSignUp, btnExit;


		lHead = new JLabel("WELCOME TO LOGIN PAGE");
		f.add(lHead);
		lHead.setBounds(120, 25, 1500, 150);
		lHead.setForeground(Color.blue);
		lHead.setFont(lHead.getFont().deriveFont(20.0f));
		
		// user label
		lUser = new JLabel("USERNAME");
		f.add(lUser);
		lUser.setBounds(110, 115, 100, 100);

		lpsw = new JLabel("PASSWORD");
		f.add(lpsw);
		lpsw.setBounds(110, 165, 100, 100);

		tfUser = new JTextField(30);
		f.add(tfUser);
		tfUser.setBounds(200, 150, 200, 30);

		tfpsw = new JPasswordField(30);
		f.add(tfpsw);
		tfpsw.setBounds(200, 200, 200, 30);

		btnLogin = new JButton("LOGIN");
		f.add(btnLogin);
		btnLogin.setBounds(250, 250, 100, 40);
		btnLogin.setBackground(Color.green);
		
		lneed = new JLabel("Don't Have An Account?");
		f.add(lneed);
		lneed.setBounds(120, 270, 250, 150);

		btnSignUp = new JButton("SIGNUP");
		f.add(btnSignUp);
		btnSignUp.setBounds(290, 320, 100, 40);
		btnSignUp.setBackground(Color.green);
		
		btnExit = new JButton("Exit");
		f.add(btnExit);
		btnExit.setBounds(250, 400, 100, 40);
		btnExit.setBackground(Color.red);

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String user = tfUser.getText();
				@SuppressWarnings("deprecation")
				String psw = tfpsw.getText();
				int value = userLogin(user, psw);

				if (value == 1) {
					new Dashboard();
					f.dispose();
				} else if (value == 2) {
					JOptionPane.showMessageDialog(f, "Password Not Matched ");

				} else {
					JOptionPane.showMessageDialog(f, "Username/Password Not Valid ");
				}

			}

		});

		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration();
				f.dispose();

			}
		});
		
		btnExit.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(btnExit, "Do you want to Exit");

				if (select == 0) {
					f.dispose();
				}
			}
		});

		f.setLayout(null);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public int userLogin(String username, String psw) {
		String query = "Select * from accounts where UserName='" + username + "'";

		try {
			ResultSet result = db.connection().executeQuery(query);
			

			if (result.next()) {
				if (result.getString(3).equals(psw)) {
					return 1;
				} else if (!result.getString(3).equals(psw)) {
					return 2;

				}

			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return 0;

	}

}