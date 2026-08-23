package com.employeeleave.model;

public class LeaveBalance {

    private int balanceId;
    private int employeeId;
    private int totalLeaves;
    private int usedLeaves;

    public LeaveBalance(int balanceId, int employeeId,
                        int totalLeaves, int usedLeaves) {

        this.balanceId = balanceId;
        this.employeeId = employeeId;
        this.totalLeaves = totalLeaves;
        this.usedLeaves = usedLeaves;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public int getUsedLeaves() {
        return usedLeaves;
    }
}