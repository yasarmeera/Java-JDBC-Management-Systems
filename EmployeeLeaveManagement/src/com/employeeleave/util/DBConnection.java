package com.employeeleave.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() {

		Connection con = null;

		try {

			String url = "jdbc:mysql://localhost:3306/employee_leave_system";
			String username = "root";
			String password = "Yasarmeera@24";

			con = DriverManager.getConnection(url, username, password);

			System.out.println("Database connected successfully!");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return con;
	}
}