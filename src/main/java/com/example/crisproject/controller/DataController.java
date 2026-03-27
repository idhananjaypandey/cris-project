package com.example.crisproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.example.crisproject.employee.entity.Employee;
import com.example.crisproject.employee.repo.EmployeeRepository;
import com.example.crisproject.service.DataService;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private DataService service;

    // ✅ Q2 MAIN OUTPUT (Employee + Projects)
    @GetMapping("/data")
    public List<Map<String, Object>> getAllData() {
        return service.getData();
    }

    // ✅ Simple Employee API
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }
}