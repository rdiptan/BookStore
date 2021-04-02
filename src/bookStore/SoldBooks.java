package bookStore;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

class SoldBooks {
	SoldBooks() {

		JFrame f = new JFrame("Bookstore Inventory Management System");
		JButton btnBack;
		JLabel lNo, lHead;
		String column[] = { "Book Number", "Book Name", "Publisher Name", "Date Published", "Price", "Quantity Sold" };

		lHead = new JLabel("Sold Books");
		f.add(lHead);
		lHead.setBounds(400, -20, 300, 100);
		lHead.setForeground(Color.blue);
		lHead.setFont(lHead.getFont().deriveFont(20.0f));

		String query = "Select * from soldbook";
		DbConnection db = new DbConnection();
		ArrayList<Books> book = new ArrayList<Books>();

		try {
			ResultSet result = db.connection().executeQuery(query);

			while (result.next()) {
				String BookName = result.getString("b_name");
				String PublisherName = result.getString("a_name");
				String date = result.getString("p_date");
				float price = Float.parseFloat(result.getString("price"));
				int bookNumber = Integer.parseInt(result.getString("bcode"));
				int Quantity = Integer.parseInt(result.getString("quantity"));
				Books stff = new Books(BookName, PublisherName, date, price, bookNumber, Quantity);
				book.add(stff);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		lNo = new JLabel("No of books Sold:" + book.size());
		f.add(lNo);
		lNo.setBounds(50, 410, 300, 100);

		Object data[][] = new Object[book.size()][column.length];

		for (int i = 0; i < book.size(); i++) {
			data[i][0] = book.get(i).bookNumber;
			data[i][1] = book.get(i).BookName;
			data[i][2] = book.get(i).PublisherName;
			data[i][3] = book.get(i).date;
			data[i][4] = book.get(i).price;
			data[i][5] = book.get(i).Quantity;
		}

		JTable jt = new JTable(data, column);
		JScrollPane sp = new JScrollPane(jt);
		f.add(sp);
		sp.setBounds(50, 50, 800, 400);

		// button

		btnBack = new JButton("Back");
		f.add(btnBack);
		btnBack.setBounds(30, 10, 70, 30);
		btnBack.setBackground(Color.yellow);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard();
				f.dispose();
			}
		});

		f.setLayout(null);
		f.setSize(1000, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
}
