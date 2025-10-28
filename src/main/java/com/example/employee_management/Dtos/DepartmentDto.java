package com.example.employee_management.Dtos;

public class DepartmentDto {
  private String name;
  private String createdDate;
  private String departmentHead;

  public DepartmentDto() {}

      public DepartmentDto(String name, String createdDate,String departmentHead){
        this.createdDate = createdDate;
        this.name = name;
        this.departmentHead = departmentHead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

  public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }
}



