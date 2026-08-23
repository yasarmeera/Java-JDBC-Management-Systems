package com.employeeleave.model;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String department;
    private String designation;

    public Employee(int employeeId, String employeeName,
                    String department, String designation) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.designation = designation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }
}