package com.example.crisproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.crisproject.employee.entity.Employee;
import com.example.crisproject.employee.repo.EmployeeRepository;

@Service
public class FileService {

    private final String UPLOAD_DIR = "C:/uploads/";

    @Autowired
    private EmployeeRepository repo;

    // ✅ CREATE
    public void save(Employee emp, MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;

            file.transferTo(new File(filePath));

            emp.setFileName(fileName);
            emp.setFilePath(filePath);
        }

        // ✅ IMPORTANT (ADD THIS)
        emp.setEmail(emp.getEmail());

        repo.save(emp);
    }

    // ✅ READ
    public List<Employee> getAll() {
        return repo.findAll();
    }

    // ✅ DELETE
    public void delete(Long id) {
        Employee emp = repo.findById(id).orElse(null);

        if (emp != null) {
            if (emp.getFilePath() != null) {
                File file = new File(emp.getFilePath());
                if (file.exists()) file.delete();
            }
            repo.deleteById(id);
        }
    }

    // ✅ GET BY ID
    public Employee getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // ✅ UPDATE
    public void update(Employee emp, MultipartFile file) throws IOException {

        Employee existing = repo.findById(emp.getId()).orElse(null);

        if (existing == null) return;

        // 👉 If new file uploaded
        if (file != null && !file.isEmpty()) {

            if (existing.getFilePath() != null) {
                File oldFile = new File(existing.getFilePath());
                if (oldFile.exists()) oldFile.delete();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;

            file.transferTo(new File(filePath));

            existing.setFileName(fileName);
            existing.setFilePath(filePath);
        }

        // ✅ UPDATE ALL FIELDS
        existing.setName(emp.getName());
        existing.setEmail(emp.getEmail());   // 🔥 ADD THIS
        existing.setRole(emp.getRole());
        existing.setDescription(emp.getDescription());
        existing.setGender(emp.getGender());

        repo.save(existing);
    }
}