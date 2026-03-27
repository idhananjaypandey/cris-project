package com.example.crisproject.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crisproject.employee.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}