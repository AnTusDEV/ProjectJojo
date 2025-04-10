package com.example.ProjectJojo.service;

import com.example.ProjectJojo.entity.Menu;
import com.example.ProjectJojo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {  
        return menuRepository.findAll(); // Fetch all menus, including parent-child relationships
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    } 

    public void deleteMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
        } else {
            throw new RuntimeException("Menu not found with id: " + id);
        }
    }
}