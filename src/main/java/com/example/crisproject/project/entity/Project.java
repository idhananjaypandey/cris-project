package com.example.crisproject.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    // ✅ IMPORTANT (manual mapping for multi-DB)
    @Column(name = "employee_id")
    private Long employeeId;

    // getters setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
}