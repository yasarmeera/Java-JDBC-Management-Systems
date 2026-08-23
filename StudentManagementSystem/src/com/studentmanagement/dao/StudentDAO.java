package com.studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.studentmanagement.model.Student;
import com.studentmanagement.util.DBConnection;

public class StudentDAO {

	// Add Student
	public void addStudent(Student student) {

		String sql = "INSERT INTO students (student_id, student_name, department) VALUES (?, ?, ?)";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, student.getStudentId());
			ps.setString(2, student.getStudentName());
			ps.setString(3, student.getDepartment());

			ps.executeUpdate();

			System.out.println("Student added successfully!");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// View Students
	public void viewStudents() {

		String sql = "SELECT * FROM students";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("student_id");
				String name = rs.getString("student_name");
				String department = rs.getString("department");

				System.out.println(id + " | " + name + "\t| " + department);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// Search Student
	public void searchStudent(int studentId) {

		String sql = "SELECT * FROM students WHERE student_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, studentId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("Student Found!");
				System.out.println("ID: " + rs.getInt("student_id"));
				System.out.println("Name: " + rs.getString("student_name"));
				System.out.println("Department: " + rs.getString("department"));

			} else {

				System.out.println("Student not found!");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// Update Student
	public void updateStudent(Student student) {

		String sql = "UPDATE students SET student_name = ?, department = ? WHERE student_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getDepartment());
			ps.setInt(3, student.getStudentId());

			int rows = ps.executeUpdate();

			if (rows > 0) {

				System.out.println("Student updated successfully!");

			} else {

				System.out.println("Student not found!");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// Delete Student
	public void deleteStudent(int studentId) {

		String sql = "DELETE FROM students WHERE student_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, studentId);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				System.out.println("Student deleted successfully!");

			} else {

				System.out.println("Student not found!");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}