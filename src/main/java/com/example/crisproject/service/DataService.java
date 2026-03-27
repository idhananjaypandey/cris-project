package com.example.crisproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.example.crisproject.employee.entity.Employee;
import com.example.crisproject.employee.repo.EmployeeRepository;
import com.example.crisproject.project.entity.Project;
import com.example.crisproject.project.repo.ProjectRepository;

@Service
public class DataService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ProjectRepository projectRepo;

    // ✅ Q2 MAIN LOGIC (MULTI-DB MANUAL MAPPING)
    public List<Map<String, Object>> getData() {

        List<Employee> employees = employeeRepo.findAll();
        List<Project> projects = projectRepo.findAll();

        // 🔹 Map employeeId → list of projects
        Map<Long, List<Project>> projectMap = new HashMap<>();

        for (Project p : projects) {
            if (p.getEmployeeId() != null) {
                projectMap
                        .computeIfAbsent(p.getEmployeeId(), k -> new ArrayList<>())
                        .add(p);
            }
        }

        // 🔹 Final result
        List<Map<String, Object>> result = new ArrayList<>();

        for (Employee e : employees) {
            Map<String, Object> obj = new HashMap<>();

            obj.put("employee", e);
            obj.put("projects", projectMap.getOrDefault(e.getId(), new ArrayList<>()));

            result.add(obj);
        }

        return result;
    }
}