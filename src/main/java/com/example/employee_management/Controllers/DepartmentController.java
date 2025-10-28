package com.example.employee_management.Controllers;

import com.example.employee_management.Dtos.DepartmentDto;
import com.example.employee_management.Dtos.EmployeesInDepartment;
import com.example.employee_management.Dtos.PaginatedResponse;
import com.example.employee_management.Entities.Department;
import com.example.employee_management.Services.DepartmentService;
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
@RequestMapping("/department")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestBody Department department){
        String response = departmentService.createDepartment(department);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Integer id){
        String response = departmentService.deleteDepartment(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Integer id,@RequestBody Department department){
        String response = departmentService.updateDepartment(id,department);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<PaginatedResponse<DepartmentDto>> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        PaginatedResponse<DepartmentDto> response = departmentService.getAllDepartments(PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(
            @PathVariable Integer id,
            @RequestParam(required = false) String expand,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        if ("employee".equalsIgnoreCase(expand)) {
            PaginatedResponse<EmployeesInDepartment> response =
                    departmentService.getDepartmentWithEmployees(id, PageRequest.of(page, size));
            if (response == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response);
        } else {
            DepartmentDto department = departmentService.getDepartmentById(id);
            if (department == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(department);
        }
    }
}
