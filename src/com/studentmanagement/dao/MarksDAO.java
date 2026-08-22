package com.studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.studentmanagement.model.Marks;
import com.studentmanagement.util.DBConnection;

public class MarksDAO {

	public void addMarks(Marks marks) {

		String sql = "INSERT INTO marks " + "(mark_id, student_id, course_id, marks) " + "VALUES (?, ?, ?, ?)";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, marks.getMarkId());
			ps.setInt(2, marks.getStudentId());
			ps.setInt(3, marks.getCourseId());
			ps.setInt(4, marks.getMarks());

			ps.executeUpdate();

			System.out.println("Marks added successfully!");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}