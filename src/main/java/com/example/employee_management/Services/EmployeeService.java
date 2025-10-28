package com.example.employee_management.Services;

import com.example.employee_management.Dtos.EmployeeDto;
import com.example.employee_management.Dtos.EmployeeLookUp;
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
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public String createNewEmployee(Employee employee){
        employeeRepository.save(employee);
        return "Employee created";
    }

    public String updateEmployee(Integer id,Employee employee){
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent()){
            Employee updatedEmployee = employee1.get();
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setAddress(employee.getAddress());
            updatedEmployee.setDepartment(employee.getDepartment());
            updatedEmployee.setRole(employee.getRole());
            updatedEmployee.setDateOfBirth(employee.getDateOfBirth());
            updatedEmployee.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());
            updatedEmployee.setJoiningDate(employee.getJoiningDate());
            updatedEmployee.setReportingManager(employee.getReportingManager());
            updatedEmployee.setSalary(employee.getSalary());

            employeeRepository.save(updatedEmployee);

            return "Employee Updated";
        }
        else {
            return "Employee not found";
        }
    }

    public String updateEmployeeDepartment(Integer employeeId, Integer departmentId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isEmpty()) {
            return "Employee not found";
        }

        Optional<Department> departmentOpt = departmentRepository.findById(departmentId);
        if (departmentOpt.isEmpty()) {
            return "Department not found";
        }

        Employee employee = employeeOpt.get();
        Department newDepartment = departmentOpt.get();

        employee.setDepartment(newDepartment);
        employeeRepository.save(employee);

        return "Employee Updated";
    }

    public PaginatedResponse<EmployeeDto> getAllEmployees(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<EmployeeDto> dtoList = new ArrayList<>();
        for (Employee e : employeePage.getContent()) {
            EmployeeDto dto = new EmployeeDto();
            dto.setEmployeeId(e.getEmployeeId());
            dto.setName(e.getName());
            dto.setDateOfBirth(e.getDateOfBirth());
            dto.setSalary(e.getSalary());
            dto.setAddress(e.getAddress());
            dto.setRole(e.getRole());
            dto.setJoiningDate(e.getJoiningDate());
            dto.setYearlyBonusPercentage(e.getYearlyBonusPercentage());
            dto.setDepartment(e.getDepartment() != null ? e.getDepartment().getName() : "No Department");
            dto.setReportingManager(e.getReportingManager() != null ? e.getReportingManager().getName() : "No Manager");
            dtoList.add(dto);
        }

        return new PaginatedResponse<>(
                dtoList,
                employeePage.getNumber(),
                employeePage.getTotalPages(),
                employeePage.getTotalElements()
        );
    }

    public PaginatedResponse<EmployeeLookUp> getEmployeeLookUp(Pageable pageable) {
        Page<Employee> page = employeeRepository.findAll(pageable);

        List<EmployeeLookUp> employeeLookUps = page.getContent().stream()
                .map(emp -> new EmployeeLookUp(emp.getEmployeeId(), emp.getName()))
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                employeeLookUps,
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

}
