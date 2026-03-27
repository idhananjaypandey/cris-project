package com.example.crisproject.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crisproject.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}