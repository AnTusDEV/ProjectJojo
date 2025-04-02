package com.example.ProjectJojo.controller;

import com.example.ProjectJojo.entity.User;
import com.example.ProjectJojo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import java.text.DecimalFormat;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Lấy danh sách tất cả người dùng với phân trang
    @GetMapping
    public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<User> users = userService.getAllUsers(pageable);

            // Định dạng giá tiền (không có ký hiệu "₫")
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            List<Map<String, Object>> formattedUsers = users.getContent().stream().map(user -> {
                Map<String, Object> userMap = new LinkedHashMap<>(); // Sử dụng LinkedHashMap để duy trì thứ tự
                userMap.put("id", user.getId()); // Đưa id lên đầu
                userMap.put("name", user.getName());
                userMap.put("linkUrl", user.getLinkUrl());
                userMap.put("discout", user.getDiscout());
                userMap.put("price", decimalFormat.format(user.getPrice())); // Định dạng giá
                return userMap;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(formattedUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lấy danh sách người dùng: " + e.getMessage());
        }
    }

    // Lấy thông tin chi tiết của một người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);

            // Định dạng giá tiền (không có ký hiệu "₫")
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            Map<String, Object> userMap = new LinkedHashMap<>(); // Sử dụng LinkedHashMap để duy trì thứ tự
            userMap.put("id", user.getId()); // Đưa id lên đầu
            userMap.put("name", user.getName());
            userMap.put("linkUrl", user.getLinkUrl());
            userMap.put("discout", user.getDiscout());
            userMap.put("price", decimalFormat.format(user.getPrice())); // Định dạng giá

            return ResponseEntity.ok(userMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lấy thông tin người dùng: " + e.getMessage());
        }
    }

    // Tạo mới một người dùng
    @PostMapping("/createdUser")
    public User createUser(@RequestBody User user) {
        User savedUser = null;
        try {
            savedUser = userService.saveUser(user);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        return savedUser;
    }

    // Cập nhật thông tin người dùng theo ID
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.saveUser(user);
    }

    // Xóa người dùng theo ID (chỉ cho phép người dùng có vai trò ADMIN)
    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Chỉ cho phép người dùng có vai trò ADMIN
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Đã xóa thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xóa người dùng: " + e.getMessage());
        }
    }
}
