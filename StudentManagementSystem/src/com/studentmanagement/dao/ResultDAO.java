package com.studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.studentmanagement.util.DBConnection;

public class ResultDAO {

	public void generateResult() {

		String sql = "SELECT s.student_id, s.student_name, " + "c.course_name, m.marks, " + "CASE "
				+ "WHEN m.marks >= 90 THEN 'A+' " + "WHEN m.marks >= 80 THEN 'A' " + "WHEN m.marks >= 70 THEN 'B' "
				+ "WHEN m.marks >= 60 THEN 'C' " + "ELSE 'F' " + "END AS grade " + "FROM students s "
				+ "JOIN marks m ON s.student_id = m.student_id " + "JOIN courses c ON m.course_id = c.course_id";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			System.out.println("\n===== Student Results =====");

			while (rs.next()) {

				System.out.println(rs.getInt("student_id") + " | " + rs.getString("student_name") + "\t| "
						+ rs.getString("course_name") + "\t| " + rs.getInt("marks") + "\t| " + rs.getString("grade"));
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}