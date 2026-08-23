package com.employeeleave.model;

import java.sql.Date;

public class LeaveRequest {

	private int leaveId;
	private int employeeId;
	private String leaveType;
	private Date fromDate;
	private Date toDate;
	private String status;

	public LeaveRequest(int leaveId, int employeeId, String leaveType, Date fromDate, Date toDate, String status) {

		this.leaveId = leaveId;
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.status = status;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public String getStatus() {
		return status;
	}
}