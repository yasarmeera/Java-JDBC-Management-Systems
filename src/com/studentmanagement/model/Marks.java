package com.studentmanagement.model;

public class Marks {

    private int markId;
    private int studentId;
    private int courseId;
    private int marks;

    public Marks(int markId, int studentId, int courseId, int marks) {
        this.markId = markId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
    }

    public int getMarkId() {
        return markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getMarks() {
        return marks;
    }
}