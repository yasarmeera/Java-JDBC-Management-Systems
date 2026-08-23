package com.employeeleave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.employeeleave.util.DBConnection;
public class LeaveBalanceDAO {

    public void viewLeaveBalance(int employeeId) {

        String sql = "SELECT * FROM leave_balance WHERE employee_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int totalLeaves = rs.getInt("total_leaves");
                int usedLeaves = rs.getInt("used_leaves");

                int remainingLeaves = totalLeaves - usedLeaves;

                System.out.println("\n===== Leave Balance =====");

                System.out.println("Employee ID: " + employeeId);
                System.out.println("Total Leaves: " + totalLeaves);
                System.out.println("Used Leaves: " + usedLeaves);
                System.out.println("Remaining Leaves: " + remainingLeaves);

            } else {

                System.out.println("Leave balance not found!");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}