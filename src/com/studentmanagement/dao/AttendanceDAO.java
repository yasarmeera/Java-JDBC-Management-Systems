package com.studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.studentmanagement.model.Attendance;
import com.studentmanagement.util.DBConnection;

public class AttendanceDAO {

    public void addAttendance(Attendance attendance) {

        String sql = "INSERT INTO attendance "
                   + "(attendance_id, student_id, course_id, attendance_date, status) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, attendance.getAttendanceId());
            ps.setInt(2, attendance.getStudentId());
            ps.setInt(3, attendance.getCourseId());
            ps.setDate(4, attendance.getAttendanceDate());
            ps.setString(5, attendance.getStatus());

            ps.executeUpdate();

            System.out.println("Attendance added successfully!");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}