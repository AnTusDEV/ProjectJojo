package com.example.ProjectJojo.controller;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;  

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/users")
public class MainController {  
    private static String DB_URL = "jdbc:mysql://localhost:3306/db_shop";
    private static String USER_NAME = "root";
    private static String PASSWORD = "root"; 
    private static ResultSet rs;

    @GetMapping("/getString")
    public ArrayList getString() {
        ArrayList data = new ArrayList<>(); 
        try {  
            rs =  respinCallData();

            while (rs.next()) { 
                Map<String, Object> nguoi = new HashMap<>();
                
                nguoi.put("id", rs.getString("id"));
                nguoi.put("name", rs.getString("name"));
                nguoi.put("linkUrl", rs.getString("linkUrl"));
                nguoi.put("price", rs.getString("price"));
                nguoi.put("discout", rs.getString("discout"));
                data.add(nguoi);
            }   
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return data;
    }

    public static ResultSet respinCallData() throws SQLException {
        Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD); 
        Statement stmt = conn.createStatement(); 
        ResultSet rso = stmt.executeQuery("select * from user");
        // System.out.println(rs.next());  
        return rso;
    }

    @GetMapping("/getStringName")
    public Map<String, Object> getStringNameName() {
        Map<String, Object> nguoi = new HashMap<>();
        nguoi.put("ten", "TrầnTrần Văn A");
        nguoi.put("tuoi", 22);
        nguoi.put("nghe", "BA");
        String[] soThich = {"Đọc sách", "Du lịch", "Thể thao"};
        nguoi.put("soThich", soThich); 
        return nguoi;
    }

    public static Connection getConnection(String dbURL, String userName, 
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    } 
}
