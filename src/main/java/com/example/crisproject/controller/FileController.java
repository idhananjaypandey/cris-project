package com.example.crisproject.controller;

import com.example.crisproject.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.crisproject.service.FileService;

@Controller
public class FileController {

    @Autowired
    private FileService service;

    // CREATE
    @PostMapping("/upload")
    public String save(
            @ModelAttribute Employee emp,
            @RequestParam MultipartFile file
    ) throws Exception {

        service.save(emp, file);
        return "redirect:/files";
    }

    // READ
    @GetMapping("/files")
    public String show(Model model) {
        model.addAttribute("list", service.getAll());
        return "files";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/files";
    }

    // EDIT PAGE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("emp", service.getById(id));
        return "edit";
    }

    // UPDATE
    @PostMapping("/update")
    public String update(
            @ModelAttribute Employee emp,
            @RequestParam MultipartFile file
    ) throws Exception {

        service.update(emp, file);
        return "redirect:/files";
    }
}