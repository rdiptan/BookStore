package bookStore;

import java.sql.*;

public class DbConnection {

	public Statement connection() {
		Connection con;
		Statement stmt = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore", "root", "regmi321");
			stmt = con.createStatement();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return stmt;

	}
}