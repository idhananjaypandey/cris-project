package com.example.crisproject.employee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String email;
    private String description;
    private String gender;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;
}