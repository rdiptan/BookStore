package bookStore;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableModel;

class View {
	View() {

		JFrame f = new JFrame("Bookstore Inventory Management System");
		JButton btnBack, btnDelete, btnUpdate, btnSell, btnSearch;
		JLabel lNo, lHead;
		String column[] = { "Book Number", "Book Name", "Publisher Name", "Date Published", "Price",
				"Quantity Available" };

		lHead = new JLabel("Available Books");
		f.add(lHead);
		lHead.setBounds(400, 30, 300, 100);
		lHead.setForeground(Color.blue);
		lHead.setFont(lHead.getFont().deriveFont(20.0f));

		String query = "Select * from books where quantity >0";
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

		lNo = new JLabel("Books available in Store:" + book.size());
		f.add(lNo);
		lNo.setBounds(50, 360, 300, 100);

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
		sp.setBounds(50, 100, 800, 300);

		// button

		btnBack = new JButton("Back");
		f.add(btnBack);
		btnBack.setBounds(10, 10, 70, 30);
		btnBack.setBackground(Color.yellow);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard();
				f.dispose();
			}
		});

		btnDelete = new JButton("Delete");
		f.add(btnDelete);
		btnDelete.setBounds(650, 400, 150, 30);
		btnDelete.setBackground(Color.red);

		btnDelete.addActionListener(e -> {
			int row = jt.getSelectedRow();
			if (row >= 0) {

				TableModel model = jt.getModel();

				String code = Integer.toString((int) model.getValueAt(row, 0));

				String dquery = "Delete from books WHERE `bcode` = '" + code + "'";
				try {
					int dresult = db.connection().executeUpdate(dquery);
					if (dresult >= 1) {
						JOptionPane.showMessageDialog(sp, "Book Deleted");
						new View();
						f.dispose();
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(sp, "please select row");
			}

		});

		btnUpdate = new JButton("Update");
		f.add(btnUpdate);
		btnUpdate.setBounds(475, 400, 150, 30);
		btnUpdate.setBackground(Color.yellow);

		btnUpdate.addActionListener(e -> {
			int row = jt.getSelectedRow();
			if (row >= 0) {

				JLabel lbName = new JLabel("Book Name");
				f.add(lbName);
				lbName.setBounds(10, 500, 900, 30);

				JTextField tfbName = new JTextField();
				f.add(tfbName);
				tfbName.setBounds(100, 500, 120, 30);

				TableModel model = jt.getModel();
				String BookName = (String) model.getValueAt(row, 1);

				tfbName.setText(BookName);

				JLabel laName = new JLabel("Publisher Name");
				f.add(laName);
				laName.setBounds(230, 500, 300, 30);

				JTextField tfaName = new JTextField();
				f.add(tfaName);
				tfaName.setBounds(350, 500, 120, 30);

				TableModel model1 = jt.getModel();
				String PublisherName = (String) model1.getValueAt(row, 2);

				tfaName.setText(PublisherName);

				JLabel lpDate = new JLabel("Date");
				f.add(lpDate);
				lpDate.setBounds(480, 500, 300, 30);

				JTextField tfpDate = new JTextField();
				f.add(tfpDate);
				tfpDate.setBounds(530, 500, 150, 30);

				TableModel model2 = jt.getModel();

				String date = (String) model2.getValueAt(row, 3);
				tfpDate.setText(date);

				JLabel laddBy = new JLabel("Price");
				f.add(laddBy);

				laddBy.setBounds(690, 500, 300, 30);

				JTextField tfaddBy = new JTextField();
				f.add(tfaddBy);
				tfaddBy.setBounds(740, 500, 150, 30);

				TableModel model3 = jt.getModel();

				String price1 = Float.toString((float) model3.getValueAt(row, 4));
				tfaddBy.setText(price1);

				JButton btnChange = new JButton("Make Change");
				f.add(btnChange);
				btnChange.setBounds(300, 550, 150, 30);
				btnChange.setBackground(Color.yellow);

				JButton btnCancel = new JButton("Cancel");
				f.add(btnCancel);
				btnCancel.setBounds(500, 550, 150, 30);
				btnCancel.setBackground(Color.yellow);

				// update action

				btnChange.addActionListener(e3 -> {

					String tbName = tfbName.getText();
					String taName = tfaName.getText();
					String tpDate = tfpDate.getText();
					String price = tfaddBy.getText();

					TableModel model4 = jt.getModel();
					String Code = Integer.toString((int) model4.getValueAt(row, 0));
					model4.getValueAt(row, 1);
					String uquery = "update `books` set `b_name` = '" + tbName + "',`a_name` = '" + taName
							+ "', `price` = '" + price + "',  `p_date` = '" + tpDate + "' WHERE `bcode` = '" + Code
							+ "'";

					try {
						int uresult = db.connection().executeUpdate(uquery);

						if (uresult >= 1) {
							JOptionPane.showMessageDialog(sp, "Book Updated");
							new View();
							f.dispose();
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				});

				// cancel action

				btnCancel.addActionListener(e2 -> {

					new View();
					f.dispose();

				});

			} else {
				JOptionPane.showMessageDialog(sp, "selected Row");
			}

		});

		JTextField tfbSearch = new JTextField();
		f.add(tfbSearch);
		tfbSearch.setBounds(50, 70, 170, 30);

		btnSearch = new JButton("Search");
		f.add(btnSearch);
		btnSearch.setBounds(230, 70, 100, 30);
		btnSearch.setBackground(Color.green);

		btnSearch.addActionListener(e3 -> {
			String Search = tfbSearch.getText();
			String query2 = "select * from books where a_name='" + Search + "' or b_name='" + Search + "' or p_date='"
					+ Search + "'";
			try {
				ResultSet result4 = db.connection().executeQuery(query2);
				if (result4.next()) {
					int bookNumber = Integer.parseInt(result4.getString("bcode"));

					BinarySearch se = new BinarySearch();
					ArrayList<Integer> Quan = new ArrayList<Integer>();
					for (int i = 0; i < book.size(); i++) {
						int quann = book.get(i).bookNumber;
						Quan.add(quann);

					}

					int Index = se.binarySearch(Quan, bookNumber);
					Object data1[][] = new Object[1][column.length];
					JTable jt1 = new JTable(data1, column);
					JScrollPane sp1 = new JScrollPane(jt1);
					f.add(sp1);
					sp1.setBounds(50, 450, 800, 38);

					data1[0][0] = book.get(Index).bookNumber;
					data1[0][1] = book.get(Index).BookName;
					data1[0][2] = book.get(Index).PublisherName;
					data1[0][3] = book.get(Index).date;
					data1[0][4] = book.get(Index).price;
					data1[0][5] = book.get(Index).Quantity;
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		});

		btnSell = new JButton("Sell");
		f.add(btnSell);
		btnSell.setBounds(300, 400, 150, 30);
		btnSell.setBackground(Color.green);

		btnSell.addActionListener(e -> {
			int row = jt.getSelectedRow();

			if (row >= 0) {

				TableModel model = jt.getModel();

				String code = Integer.toString((int) model.getValueAt(row, 0));

				String BookName = (String) model.getValueAt(row, 1);
				String PublisherName = (String) model.getValueAt(row, 2);
				String date = (String) model.getValueAt(row, 3);

				String price = Float.toString((float) model.getValueAt(row, 4));
				int Quan = 1;
				int Quantity = ((int) model.getValueAt(row, 5) - 1);

				String query1 = "Select * from soldbook where b_name='" + BookName + "'";
				try {
					db.connection().executeQuery(query1);

					String dquery = "update `books` set `quantity` = '" + Quantity + "' WHERE `bcode` = '" + code + "'";
					String aquery = "insert into soldbook(bcode, b_name,a_name,p_date,price,quantity) values('" + code
							+ "', '" + BookName + "','" + PublisherName + "','" + date + "','" + price + "','" + Quan
							+ "') ";
					try {
						int dresult = db.connection().executeUpdate(dquery);
						int aresult = db.connection().executeUpdate(aquery);
						if (aresult >= 1 && dresult >= 1) {
							JOptionPane.showMessageDialog(sp, "Book Sold");
							new View();
							f.dispose();
						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				} catch (SQLException e2) {

					e2.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(sp, "please select row");
			}

		});

		f.setLayout(null);
		f.setSize(1000, 700);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
}
