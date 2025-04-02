package com.example.ProjectJojo.controller; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 
import java.util.Random;

import com.example.ProjectJojo.entity.User;
import com.example.ProjectJojo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomData { 
    @Autowired
    private UserService userService;

    // Cập nhật tất cả linkUrl của người dùng với URL ảnh ngẫu nhiên
    @PutMapping("/updateAllLinkUrls")
    public ResponseEntity<Object> updateAllLinkUrls() {
        try {
            List<User> users = userService.getAllUsers(); // Lấy danh sách tất cả người dùng
            Random random = new Random();

            // Danh sách các URL ảnh
            List<String> imageUrls = Arrays.asList(
                "https://example.com/image1.jpg",
                "https://example.com/image2.jpg",
                "https://example.com/image3.jpg",
                "https://example.com/image4.jpg",
                "https://example.com/image5.jpg",
                "https://example.com/image6.jpg",
                "https://example.com/image7.jpg",
                "https://example.com/image8.jpg",
                "https://example.com/image9.jpg",
                "https://example.com/image10.jpg"
            );

            for (User user : users) {
                // Chọn ngẫu nhiên một URL ảnh từ danh sách
                String randomImageUrl = imageUrls.get(random.nextInt(imageUrls.size()));
                user.setLinkUrl(randomImageUrl); // Cập nhật linkUrl
                userService.saveUser(user); // Lưu thay đổi vào cơ sở dữ liệu
            }

            return ResponseEntity.ok("Đã cập nhật linkUrl của tất cả người dùng thành công với URL ảnh ngẫu nhiên.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật linkUrl: " + e.getMessage());
        }
    }

    // Cập nhật tất cả tên người dùng với tên món ăn vặt ngẫu nhiên
    @PutMapping("/updateAllNames")
    public ResponseEntity<Object> updateAllNames() {
        try {
            List<User> users = userService.getAllUsers(); // Lấy danh sách tất cả người dùng
            Random random = new Random();

            // Danh sách tên món ăn vặt
            List<String> snackNames = Arrays.asList(
                "Bánh tráng trộn", "Trà sữa", "Xoài lắc", "Bánh mì nướng muối ớt", 
                "Chè Thái", "Kem dừa", "Bánh bao chiên", "Khoai lang kén", 
                "Gỏi cuốn", "Bánh xèo", "Nem chua rán", "Bánh gạo cay"
            );

            for (User user : users) {
                // Chọn ngẫu nhiên một tên món ăn vặt từ danh sách
                String randomSnackName = snackNames.get(random.nextInt(snackNames.size()));
                user.setName(randomSnackName); // Cập nhật tên
                userService.saveUser(user); // Lưu thay đổi vào cơ sở dữ liệu
            }

            return ResponseEntity.ok("Đã cập nhật tên của tất cả người dùng thành công với tên món ăn vặt ngẫu nhiên.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật tên: " + e.getMessage());
        }
    }

    // Tạo nhiều người dùng
    @PostMapping("/createUsers/{number}")
    public ResponseEntity<Object> createMultipleUsers(@RequestBody User userTemplate, @PathVariable int number) {
        List<User> savedUsers = new ArrayList<>();
        try {
            for (int i = 0; i < number; i++) {
                User newUser = new User();
                newUser.setName(userTemplate.getName() + i);  // Tạo tên mới dựa trên mẫu
                newUser.setLinkUrl(userTemplate.getLinkUrl()); // Sử dụng linkUrl từ mẫu
                newUser.setPrice(userTemplate.getPrice()); // Sử dụng giá từ mẫu
                savedUsers.add(userService.saveUser(newUser)); // Lưu người dùng mới vào cơ sở dữ liệu
            }
            return ResponseEntity.ok(savedUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tạo người dùng: " + e.getMessage());
        }
    }
}
