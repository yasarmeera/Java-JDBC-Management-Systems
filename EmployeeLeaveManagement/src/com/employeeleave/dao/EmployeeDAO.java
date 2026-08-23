package com.employeeleave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.employeeleave.model.Employee;
import com.employeeleave.util.DBConnection;

public class EmployeeDAO {

	public void addEmployee(Employee employee) {

		String sql = "INSERT INTO employees " + "(employee_id, employee_name, department, designation) "
				+ "VALUES (?, ?, ?, ?)";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, employee.getEmployeeId());
			ps.setString(2, employee.getEmployeeName());
			ps.setString(3, employee.getDepartment());
			ps.setString(4, employee.getDesignation());

			ps.executeUpdate();

			System.out.println("Employee added successfully!");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void viewEmployees() {

		String sql = "SELECT * FROM employees";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			System.out.println("\n===== Employee List =====");

			while (rs.next()) {

				System.out.println(
						rs.getInt("employee_id") + " | " + 
						rs.getString("employee_name") + "\t| " + 
						rs.getString("department") + "\t| " + 
						rs.getString("designation"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void searchEmployee(int employeeId) {

		String sql = "SELECT * FROM employees WHERE employee_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, employeeId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("\nEmployee Found!");
				System.out.println("ID: " + rs.getInt("employee_id"));
				System.out.println("Name: " + rs.getString("employee_name"));
				System.out.println("Department: " + rs.getString("department"));
				System.out.println("Designation: " + rs.getString("designation"));

			} else {

				System.out.println("Employee not found!");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}