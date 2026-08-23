package com.studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.studentmanagement.model.Enrollment;
import com.studentmanagement.util.DBConnection;

public class EnrollmentDAO {

	public void addEnrollment(Enrollment enrollment) {

		String sql = "INSERT INTO enrollments " + "(enrollment_id, student_id, course_id, enrollment_date) "
				+ "VALUES (?, ?, ?, ?)";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, enrollment.getEnrollmentId());
			ps.setInt(2, enrollment.getStudentId());
			ps.setInt(3, enrollment.getCourseId());
			ps.setDate(4, enrollment.getEnrollmentDate());

			ps.executeUpdate();

			System.out.println("Student enrolled successfully!");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}