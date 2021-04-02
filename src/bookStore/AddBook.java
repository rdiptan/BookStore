package bookStore;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

class AddBook {
	AddBook() {
		JFrame f = new JFrame("Bookstore Inventory Management System");

		JLabel lHead, lbName, laName, lpDate, lprice, lQuantity;
		JTextField tfbName, tfaName, tfpDate, tfprice, tfQuantity;
		JButton btnSave, btnBack, btnView, btnClear;

		lHead = new JLabel("Add Books...");
		f.add(lHead);
		lHead.setBounds(150, 10, 300, 100);
		lHead.setForeground(Color.blue);
		lHead.setFont(lHead.getFont().deriveFont(20.0f));

		// user_label
		lbName = new JLabel("Book Name:");
		f.add(lbName);
		lbName.setBounds(80, 100, 120, 30);

		laName = new JLabel("Publisher Name:");
		f.add(laName);
		laName.setBounds(80, 100, 120, 100);

		lpDate = new JLabel("Publish Date:");
		f.add(lpDate);
		lpDate.setBounds(80, 100, 120, 170);

		lprice = new JLabel("Price:");
		f.add(lprice);
		lprice.setBounds(80, 100, 120, 240);

		lQuantity = new JLabel("Quantity:");
		f.add(lQuantity);
		lQuantity.setBounds(80, 100, 120, 310);

		tfbName = new JTextField(30);
		f.add(tfbName);
		tfbName.setBounds(200, 105, 180, 20);

		tfaName = new JTextField(30);
		f.add(tfaName);
		tfaName.setBounds(200, 140, 180, 20);

		tfpDate = new JTextField(30);
		f.add(tfpDate);
		tfpDate.setBounds(200, 175, 180, 20);

		tfprice = new JTextField(30);
		f.add(tfprice);
		tfprice.setBounds(200, 210, 180, 20);

		tfQuantity = new JTextField(30);
		f.add(tfQuantity);
		tfQuantity.setBounds(200, 250, 180, 20);

		btnSave = new JButton("Save");
		f.add(btnSave);
		btnSave.setBounds(200, 300, 150, 30);
		btnSave.setBackground(Color.green);

		btnView = new JButton("View Books");
		f.add(btnView);
		btnView.setBounds(200, 350, 150, 30);
		btnView.setBackground(Color.green);

		btnClear = new JButton("Clear");
		f.add(btnClear);
		btnClear.setBounds(200, 400, 150, 30);
		btnClear.setBackground(Color.yellow);
		
		btnBack = new JButton("Back");
		f.add(btnBack);
		btnBack.setBounds(10, 10, 70, 30);
		btnBack.setBackground(Color.yellow);
		
		
		btnClear.addActionListener(e -> {
            tfbName.setText("");
            tfaName.setText("");
            tfpDate.setText("");
            tfprice.setText("");
            tfQuantity.setText("");
        });

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new View();
				f.dispose();

			}
		});

		btnBack.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Dashboard();
				f.dispose();
			}
		});

		// save action
		btnSave.addActionListener(e -> {
			String BName = tfbName.getText();
			String PName = tfaName.getText();
			String Date = tfpDate.getText();
			String Price = tfprice.getText();
			String Quantity = tfQuantity.getText();
			if(tfbName.getText().isEmpty() || tfaName.getText().isEmpty() || tfpDate.getText().isEmpty() || tfprice.getText().isEmpty() || tfQuantity.getText().isEmpty()) {
				JOptionPane.showMessageDialog(btnSave, "Input all fields");
			}
			else {
			int value = newBooks(BName,PName,Date,Price,Quantity);
			if (value == 1) {
				JOptionPane.showMessageDialog(btnSave, "Book Added");
				tfbName.setText("");
	            tfaName.setText("");
	            tfpDate.setText("");
	            tfprice.setText("");
	            tfQuantity.setText("");
			}
			else {
				JOptionPane.showMessageDialog(btnSave, "Please Check your Input Values");
			}
			}

		});

		f.setLayout(null);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	DbConnection db = new DbConnection();
	public int newBooks(String BName, String PName, String Date, String Price, String Quantity){
		String query = "insert into books(b_name,a_name,p_date,price,quantity) values('" + BName + "','" + PName
				+ "','" + Date + "','" + Price + "','" + Quantity + "') ";
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
