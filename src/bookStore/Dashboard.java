package bookStore;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


class Dashboard {
	Dashboard() {
		JFrame f = new JFrame("Bookstore Inventory Management System");
		JButton btnAdd, btnView, btnViewSold, btnLogout;
		JLabel lTitle;

		lTitle = new JLabel("Dashboard");
		f.add(lTitle);
		lTitle.setBounds(225, 30, 3000, 100);
		lTitle.setForeground(Color.blue);
		lTitle.setFont(lTitle.getFont().deriveFont(20.0f));

		btnAdd = new JButton("Add books");
		f.add(btnAdd);
		btnAdd.setBounds(200, 150, 170, 50);
		btnAdd.setBackground(Color.green);
		
		btnView = new JButton("Available Books");
		f.add(btnView);
		btnView.setBounds(200, 250, 170, 50);
		btnView.setBackground(Color.green);

		btnViewSold = new JButton("View Sold Books");
		f.add(btnViewSold);
		btnViewSold.setBounds(200, 350, 170, 50);
		btnViewSold.setBackground(Color.green);
		
		btnLogout = new JButton("LogOut");
		f.add(btnLogout);
		btnLogout.setBounds(200, 450, 170, 50);
		btnLogout.setBackground(Color.red);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddBook();
				f.dispose();

			}
		});
		
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new View();
				f.dispose();

			}
		});

		btnViewSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SoldBooks();
				f.dispose();

			}
		});
		
		btnLogout.addActionListener((ActionListener) new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(btnLogout, "Do you want to Logout");

				if (select == 0) {
					new Login();
					f.dispose();
				}
			}
		});

		f.setLayout(null);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
