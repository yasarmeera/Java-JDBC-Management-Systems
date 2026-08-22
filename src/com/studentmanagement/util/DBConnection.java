package com.studentmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

    	String url = "jdbc:mysql://localhost:3306/school_management_system";
        String username = "root";
        String password = "Yasarmeera@24";

        try {
            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}