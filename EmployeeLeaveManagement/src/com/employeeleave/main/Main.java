package com.employeeleave.main;

import java.util.Scanner;
import com.employeeleave.dao.EmployeeDAO;
import com.employeeleave.dao.LeaveBalanceDAO;
import com.employeeleave.dao.LeaveRequestDAO;
import com.employeeleave.model.Employee;
import com.employeeleave.model.LeaveRequest;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		LeaveRequestDAO leaveRequestDAO = new LeaveRequestDAO();
		LeaveBalanceDAO leaveBalanceDAO = new LeaveBalanceDAO();

		while (true) {

			System.out.println("\n== Employee Leave Management System ==");

			System.out.println("\n1. Add Employee");
			System.out.println("2. View Employees");
			System.out.println("3. Search Employee");
			System.out.println("4. Apply Leave");
			System.out.println("5. View Leave Requests");
			System.out.println("6. View Leave Balance");
			System.out.println("7. Approve / Reject Leave");
			System.out.println("8. Exit");

			System.out.print("\nEnter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {

			// 1. ADD EMPLOYEE

			case 1:

				System.out.print("Enter Employee ID (101 onwards): ");
				int employeeId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Employee Name: ");
				String employeeName = sc.nextLine();

				System.out.print("Enter Department: ");
				String department = sc.nextLine();

				System.out.print("Enter Designation: ");
				String designation = sc.nextLine();

				Employee employee = new Employee(employeeId, employeeName, department, designation);
				employeeDAO.addEmployee(employee);
				break;

			// 2. VIEW EMPLOYEES

			case 2:

				employeeDAO.viewEmployees();
				break;

			// 3. SEARCH EMPLOYEE

			case 3:

				System.out.print("Enter Employee ID to search (101 onwards): ");
				int searchId = sc.nextInt();
				employeeDAO.searchEmployee(searchId);
				break;

			// 4. APPLY LEAVE

			case 4:

				System.out.print("Enter Leave ID (2001 onwards): ");
				int leaveId = sc.nextInt();

				System.out.print("Enter Employee ID (101 onwards): ");
				int leaveEmployeeId = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter Leave Type: ");
				String leaveType = sc.nextLine();

				System.out.print("Enter From Date (yyyy-mm-dd): ");
				String fromDateInput = sc.nextLine();

				System.out.print("Enter To Date (yyyy-mm-dd): ");
				String toDateInput = sc.nextLine();

				java.sql.Date fromDate = java.sql.Date.valueOf(fromDateInput);
				java.sql.Date toDate = java.sql.Date.valueOf(toDateInput);

				LeaveRequest leaveRequest = new LeaveRequest(leaveId, leaveEmployeeId, leaveType, fromDate, toDate,
						"Pending");

				leaveRequestDAO.applyLeave(leaveRequest);
				break;

			// 5. VIEW LEAVE REQUESTS

			case 5:

				leaveRequestDAO.viewLeaveRequests();
				break;

			// 6. VIEW LEAVE BALANCE

			case 6:

				System.out.print("Enter Employee ID (101 onwards): ");
				int balanceEmployeeId = sc.nextInt();
				leaveBalanceDAO.viewLeaveBalance(balanceEmployeeId);
				break;

			// 7. APPROVE / REJECT LEAVE

			case 7:

				System.out.print("Enter Leave ID (2001 onwards): ");
				int updateLeaveId = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter Status (Approved/Rejected): ");
				String status = sc.nextLine();

				leaveRequestDAO.updateLeaveStatus(updateLeaveId, status);
				break;

			// 8. EXIT

			case 8:

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