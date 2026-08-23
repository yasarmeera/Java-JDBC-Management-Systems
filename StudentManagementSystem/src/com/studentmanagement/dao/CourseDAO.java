package com.studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.studentmanagement.model.Course;
import com.studentmanagement.util.DBConnection;

public class CourseDAO {

    public void addCourse(Course course) {

        String sql = "INSERT INTO courses (course_id, course_name, duration) VALUES (?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, course.getCourseId());
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getDuration());

            ps.executeUpdate();

            System.out.println("Course added successfully!");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}