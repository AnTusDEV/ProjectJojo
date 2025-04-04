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

    // public Menu updateMenu(Long id, Menu updatedMenu) {
    //     Menu existingMenu = menuRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
    //     existingMenu.setName(updatedMenu.getName());
    //     existingMenu.setDescription(updatedMenu.getDescription());
    //     existingMenu.setParent(updatedMenu.getParent());
    //     return menuRepository.save(existingMenu);
    // }

    // public void deleteMenu(Long id) {
    //     if (menuRepository.existsById(id)) {
    //         menuRepository.deleteById(id);
    //     } else {
    //         throw new RuntimeException("Menu not found with id: " + id);
    //     }
    // }
}