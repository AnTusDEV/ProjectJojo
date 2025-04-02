package com.example.ProjectJojo.controller;

import com.example.ProjectJojo.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();

        // Giả lập xác thực (thay thế bằng logic thực tế hoặc tích hợp Spring Security)
        if ("admin".equals(username) && "admin123".equals(password)) {
            String token = JwtUtil.generateToken(username, "ADMIN");
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("role", "ADMIN");
            response.put("token", token);
        } else if ("user".equals(username) && "user123".equals(password)) {
            String token = JwtUtil.generateToken(username, "USER");
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("role", "USER");
            response.put("token", token);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid username or password");
        }
        return response;
    }
}
