package com.employeeleave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.employeeleave.model.LeaveRequest;
import com.employeeleave.util.DBConnection;

public class LeaveRequestDAO {

	// APPLY LEAVE

	public void applyLeave(LeaveRequest leaveRequest) {

		String sql = "INSERT INTO leave_requests " + "(leave_id, employee_id, leave_type, from_date, to_date, status) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, leaveRequest.getLeaveId());
			ps.setInt(2, leaveRequest.getEmployeeId());
			ps.setString(3, leaveRequest.getLeaveType());
			ps.setDate(4, leaveRequest.getFromDate());
			ps.setDate(5, leaveRequest.getToDate());
			ps.setString(6, leaveRequest.getStatus());
			ps.executeUpdate();

			System.out.println("Leave applied successfully!");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// VIEW LEAVE REQUESTS

	public void viewLeaveRequests() {

		String sql = "SELECT * FROM leave_requests";

		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("\n===== Leave Requests =====");

			while (rs.next()) {

				System.out.println(
						rs.getInt("leave_id") + " | " + 
						rs.getInt("employee_id") + " | " + 
						rs.getString("leave_type") + " | " + 
						rs.getDate("from_date") + " | " + 
						rs.getDate("to_date") + " | " + 
						rs.getString("status"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// APPROVE / REJECT LEAVE

	public void updateLeaveStatus(int leaveId, String status) {

		Connection con = null;

		try {

			con = DBConnection.getConnection();

			// Start transaction
			con.setAutoCommit(false);

			// STEP 1: Update leave status

			String updateLeave = "UPDATE leave_requests " + "SET status = ? " + "WHERE leave_id = ?";

			PreparedStatement ps1 = con.prepareStatement(updateLeave);

			ps1.setString(1, status);
			ps1.setInt(2, leaveId);

			int rows = ps1.executeUpdate();

			if (rows == 0) {

				System.out.println("Leave request not found!");

				con.rollback();

				return;
			}

			// STEP 2: If Approved, update leave balance

			if (status.equalsIgnoreCase("Approved")) {

				String getEmployee = "SELECT employee_id " + "FROM leave_requests " + "WHERE leave_id = ?";
				PreparedStatement ps2 = con.prepareStatement(getEmployee);
				ps2.setInt(1, leaveId);
				ResultSet rs = ps2.executeQuery();

				if (rs.next()) {

					int employeeId = rs.getInt("employee_id");

					String updateBalance = "UPDATE leave_balance " + "SET used_leaves = used_leaves + 1 "
							+ "WHERE employee_id = ?";

					PreparedStatement ps3 = con.prepareStatement(updateBalance);
					ps3.setInt(1, employeeId);

					int balanceRows = ps3.executeUpdate();

					if (balanceRows == 0) {

						System.out.println("Leave balance not found!");

						con.rollback();

						return;
					}
				}
			}

			// COMMIT

			con.commit();
			System.out.println("Leave status updated successfully!");

		} catch (Exception e) {

			try {

				if (con != null) {

					con.rollback();
					System.out.println("Transaction failed. Changes rolled back.");
				}

			} catch (Exception rollbackException) {

				rollbackException.printStackTrace();
			}

			e.printStackTrace();

		} finally {

			try {

				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}