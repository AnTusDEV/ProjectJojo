package com.example.ProjectJojo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@CrossOrigin(origins = "*")
public class Cart {
    @GetMapping("/getBuyCart")
    @PreAuthorize("hasRole('ADMIN')") // Ensures the user has the ADMIN role
    public String getBuyCart() {
        return "https://d1hjkbq40fs2x4.cloudfront.net/2016-01-31/files/1045.jpg";
    }
}
