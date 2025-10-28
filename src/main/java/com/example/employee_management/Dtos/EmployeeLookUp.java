package com.example.employee_management.Dtos;

public class EmployeeLookUp {
    private Integer employeeId;
    private String name;

    public EmployeeLookUp(Integer employeeId, String name){
        this.employeeId = employeeId;
        this.name = name;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
