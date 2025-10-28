package com.example.employee_management.Controllers;

import com.example.employee_management.Dtos.EmployeeDto;
import com.example.employee_management.Dtos.EmployeeLookUp;
import com.example.employee_management.Dtos.PaginatedResponse;
import com.example.employee_management.Entities.Department;
import com.example.employee_management.Entities.Employee;
import com.example.employee_management.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        String response = employeeService.createNewEmployee(employee);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee){
        String response = employeeService.updateEmployee(id,employee);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/department/{employeeId}/{departmentId}")
    public ResponseEntity<String> updateEmployeeDepartment(
            @PathVariable Integer employeeId,
            @PathVariable Integer departmentId) {

        String message = employeeService.updateEmployeeDepartment(employeeId, departmentId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/get-all")
    public ResponseEntity<PaginatedResponse<EmployeeDto>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PaginatedResponse<EmployeeDto> response = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee-lookup")
    public ResponseEntity<?> getEmployeeLookUp(
            @RequestParam(required = true) Boolean lookup,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        if (!lookup) {
            return ResponseEntity.badRequest().body("Invalid lookup value");
        }

        PaginatedResponse<EmployeeLookUp> response = employeeService.getEmployeeLookUp(PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

}
