package com.studentmanagement.model;

import java.sql.Date;

public class Attendance {

    private int attendanceId;
    private int studentId;
    private int courseId;
    private Date attendanceDate;
    private String status;

    public Attendance(int attendanceId, int studentId, int courseId,
                      Date attendanceDate, String status) {

        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public String getStatus() {
        return status;
    }
}