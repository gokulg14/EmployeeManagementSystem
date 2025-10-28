package com.example.employee_management.Dtos;

public class EmployeesInDepartment {

    private String department;
    private Integer employeeId;
    private String name;
    private String dateOfBirth;
    private Double salary;
    private String address;
    private String role;
    private String joiningDate;
    private String yearlyBonusPercentage;
    private String reportingManager;

    public EmployeesInDepartment(String department, Integer employeeId, String name, String dateOfBirth,
                                 Double salary, String address, String role, String joiningDate,
                                 String yearlyBonusPercentage, String reportingManager) {
        this.department = department;
        this.employeeId = employeeId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.address = address;
        this.role = role;
        this.joiningDate = joiningDate;
        this.yearlyBonusPercentage = yearlyBonusPercentage;
        this.reportingManager = reportingManager;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Double getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public String getYearlyBonusPercentage() {
        return yearlyBonusPercentage;
    }

    public String getReportingManager() {
        return reportingManager;
    }
}
