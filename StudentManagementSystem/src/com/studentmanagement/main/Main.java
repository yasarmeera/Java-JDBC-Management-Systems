package com.studentmanagement.main;

import java.util.Scanner;

import com.studentmanagement.dao.AttendanceDAO;
import com.studentmanagement.dao.CourseDAO;
import com.studentmanagement.dao.EnrollmentDAO;
import com.studentmanagement.dao.MarksDAO;
import com.studentmanagement.dao.ResultDAO;
import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.model.Attendance;
import com.studentmanagement.model.Course;
import com.studentmanagement.model.Enrollment;
import com.studentmanagement.model.Marks;
import com.studentmanagement.model.Student;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		StudentDAO studentDAO = new StudentDAO();
		CourseDAO courseDAO = new CourseDAO();
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
		AttendanceDAO attendanceDAO = new AttendanceDAO();
		MarksDAO marksDAO = new MarksDAO();
		ResultDAO resultDAO = new ResultDAO();

		while (true) {

			System.out.println("\n== Student Management System ==");

			System.out.println("1. Add Student");
			System.out.println("2. View Students");
			System.out.println("3. Search Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Add Course");
			System.out.println("7. Enrollment");
			System.out.println("8. Attendance");
			System.out.println("9. Add Marks");
			System.out.println("10. Generate Result");
			System.out.println("11. Exit");

			System.out.print("\nEnter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {

			// 1. ADD STUDENT

			case 1:

				System.out.print("Enter Student ID (101 onwards): ");
				int id = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Student Name: ");
				String name = sc.nextLine();

				System.out.print("Enter Department: ");
				String department = sc.nextLine();

				Student student = new Student(id, name, department);

				studentDAO.addStudent(student);

				break;

			// 2. VIEW STUDENTS

			case 2:

				studentDAO.viewStudents();

				break;

			// 3. SEARCH STUDENT

			case 3:

				System.out.print("Enter Student ID to search (101 onwards): ");
				int searchId = sc.nextInt();

				studentDAO.searchStudent(searchId);

				break;

			// 4. UPDATE STUDENT

			case 4:

				System.out.print("Enter Student ID to update (101 onwards): ");
				int updateId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter New Student Name: ");
				String updateName = sc.nextLine();

				System.out.print("Enter New Department: ");
				String updateDepartment = sc.nextLine();

				Student updateStudent = new Student(updateId, updateName, updateDepartment);

				studentDAO.updateStudent(updateStudent);

				break;

			// 5. DELETE STUDENT

			case 5:

				System.out.print("Enter Student ID to delete (101 onwards): ");
				int deleteId = sc.nextInt();

				studentDAO.deleteStudent(deleteId);

				break;

			// 6. ADD COURSE

			case 6:

				System.out.print("Enter Course ID (1 onwards): ");
				int courseId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Course Name: ");
				String courseName = sc.nextLine();

				System.out.print("Enter Course Duration: ");
				String duration = sc.nextLine();

				Course newCourse = new Course(courseId, courseName, duration);

				courseDAO.addCourse(newCourse);

				break;

			// 7. ENROLLMENT

			case 7:

				System.out.print("Enter Enrollment ID (1001 onwards): ");
				int enrollmentId = sc.nextInt();

				System.out.print("Enter Student ID (101 onwards): ");
				int enrollmentStudentId = sc.nextInt();

				System.out.print("Enter Course ID (1 onwards): ");
				int enrollmentCourseId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Enrollment Date (yyyy-mm-dd): ");

				String date = sc.nextLine();

				java.sql.Date enrollmentDate = java.sql.Date.valueOf(date);

				Enrollment enrollment = new Enrollment(enrollmentId, enrollmentStudentId, enrollmentCourseId,
						enrollmentDate);

				enrollmentDAO.addEnrollment(enrollment);

				break;

			// 8. ATTENDANCE

			case 8:

				System.out.print("Enter Attendance ID (2001 onwards): ");
				int attendanceId = sc.nextInt();

				System.out.print("Enter Student ID (101 onwards): ");
				int attendanceStudentId = sc.nextInt();

				System.out.print("Enter Course ID (1 onwards): ");
				int attendanceCourseId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Attendance Date (yyyy-mm-dd): ");

				String attendanceDateInput = sc.nextLine();

				java.sql.Date attendanceDate = java.sql.Date.valueOf(attendanceDateInput);

				System.out.print("Enter Status (Present/Absent): ");

				String status = sc.nextLine();

				Attendance attendance = new Attendance(attendanceId, attendanceStudentId, attendanceCourseId,
						attendanceDate, status);

				attendanceDAO.addAttendance(attendance);

				break;

			// 9. ADD MARKS

			case 9:

				System.out.print("Enter Mark ID (3001 onwards): ");
				int markId = sc.nextInt();

				System.out.print("Enter Student ID (101 onwards): ");
				int marksStudentId = sc.nextInt();

				System.out.print("Enter Course ID (1 onwards): ");
				int marksCourseId = sc.nextInt();

				System.out.print("Enter Marks (0-100): ");
				int marksValue = sc.nextInt();

				Marks marks = new Marks(markId, marksStudentId, marksCourseId, marksValue);

				marksDAO.addMarks(marks);

				break;

			// 10. GENERATE RESULT

			case 10:

				resultDAO.generateResult();

				break;

			// 11. EXIT

			case 11:

				System.out.println("Thank you!");

				sc.close();

				return;

			// INVALID CHOICE

			default:

				System.out.println("Invalid choice!");

			}
		}
	}
}