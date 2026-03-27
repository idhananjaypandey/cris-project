package com.example.crisproject.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crisproject.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}