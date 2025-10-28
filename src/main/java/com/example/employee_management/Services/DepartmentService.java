package com.example.employee_management.Services;

import com.example.employee_management.Dtos.EmployeesInDepartment;
import com.example.employee_management.Dtos.DepartmentDto;
import com.example.employee_management.Dtos.PaginatedResponse;
import com.example.employee_management.Entities.Department;
import com.example.employee_management.Entities.Employee;
import com.example.employee_management.Repositories.DepartmentRepository;
import com.example.employee_management.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public String createDepartment(Department department){
        departmentRepository.save(department);
        return "Department Created";
    }

    public String deleteDepartment(Integer id){
        Optional<Department> department= departmentRepository.findById(id);
        if (department.isPresent()){
            Boolean isExist = employeeRepository.existsByDepartmentDepartmentId(id);
            if (isExist){
                return "Employee exist in Department";
            }
            else {
                departmentRepository.deleteById(id);
                return "Department deleted";
            }
        }
        else{
            return "No Such Department";
        }
    }

    public String updateDepartment(Integer id,Department department){
        Optional<Department> department1 = departmentRepository.findById(id);
        if (department1.isPresent()){
            Department updatedDepartment = department1.get();
            updatedDepartment.setDepartmentHead(department.getDepartmentHead());
            updatedDepartment.setCreatedDate(department.getCreatedDate());
            updatedDepartment.setName(department.getName());

            departmentRepository.save(updatedDepartment);
            return "Department Updated";
        }
        else {
            return "No Such Department";
        }
    }

    public PaginatedResponse<DepartmentDto> getAllDepartments(Pageable pageable) {
        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        List<DepartmentDto> dtoList = new ArrayList<>();

        for (Department department : departmentPage.getContent()) {
            DepartmentDto dto = new DepartmentDto();
            dto.setName(department.getName());
            dto.setCreatedDate(department.getCreatedDate());
            if (department.getDepartmentHead() != null) {
                dto.setDepartmentHead(department.getDepartmentHead().getName());
            } else {
                dto.setDepartmentHead("No Head Assigned");
            }
            dtoList.add(dto);
        }

        return new PaginatedResponse<>(
                dtoList,
                departmentPage.getNumber(),
                departmentPage.getTotalPages(),
                departmentPage.getTotalElements()
        );
    }

    public Optional<Department> getDepartment(Integer id){
        return departmentRepository.findById(id);
    }

    public DepartmentDto getDepartmentById(Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) return null;

        String headName = department.getDepartmentHead() != null
                ? department.getDepartmentHead().getName()
                : "Not Assigned";

        return new DepartmentDto(department.getName(), department.getCreatedDate(), headName);
    }

    public PaginatedResponse<EmployeesInDepartment> getDepartmentWithEmployees(Integer id, Pageable pageable) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) return null;

        Page<Employee> employeePage = employeeRepository.findByDepartmentDepartmentId(id, pageable);

        List<EmployeesInDepartment> employeeDtos = employeePage.getContent().stream()
                .map(emp -> new EmployeesInDepartment(
                        department.getName(),
                        emp.getEmployeeId(),
                        emp.getName(),
                        emp.getDateOfBirth(),
                        emp.getSalary(),
                        emp.getAddress(),
                        emp.getRole(),
                        emp.getJoiningDate(),
                        emp.getYearlyBonusPercentage(),
                        emp.getReportingManager() != null ? emp.getReportingManager().getName() : null
                ))
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                employeeDtos,
                employeePage.getNumber(),
                employeePage.getTotalPages(),
                employeePage.getTotalElements()
        );
    }
}
