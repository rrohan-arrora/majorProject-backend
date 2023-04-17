package com.iiitb.readandreturn;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/react-library-database?useSSL=false";
		String user = "root";
		String pass = "mysql";
		try {
			System.out.println("connecting to database: "+jdbcUrl);
			
			Connection myConn = 
					DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successful");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
