package com.example.crisproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // ✅ DEFAULT ROUTE
    @GetMapping("/")
    public String home() {
        return "redirect:/files";
    }

    // upload page
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }
}