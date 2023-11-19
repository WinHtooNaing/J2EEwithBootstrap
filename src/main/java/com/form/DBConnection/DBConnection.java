package com.form.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/javaapi";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	private static Connection con = null;
	private static DBConnection instance = null;

	private DBConnection() {
		try {
			Class.forName(DB_DRIVER);
			try {
				con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				if (con == null) {
					System.out.print("cannot connect");
				} else {
					System.out.print("connection is succeful");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
	public static DBConnection getConnection() {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	public static Connection getCon() {
		return con;
	}
	
	public static void closeCon(Connection con,PreparedStatement stmt) {
		if(con != null ) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void closeCon(Connection con,PreparedStatement stmt,ResultSet set) {
		if(con != null ) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
