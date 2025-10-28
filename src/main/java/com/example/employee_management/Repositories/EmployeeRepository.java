package com.example.employee_management.Repositories;

import com.example.employee_management.Dtos.EmployeeLookUp;
import com.example.employee_management.Entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Boolean existsByDepartmentDepartmentId(Integer id);

    Page<Employee> findByDepartmentDepartmentId(Integer id, Pageable pageable);

}
