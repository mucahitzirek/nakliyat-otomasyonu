package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public Connection con;
	public Statement st;
	public ResultSet rs;
	public java.sql.PreparedStatement pst;

	public Connection Vtb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nak" + "?useSSL=false", "root", "1234");
			st = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Veri tabanina baglanilamadi" + e.getMessage());
		}
		return con;
	}

}
