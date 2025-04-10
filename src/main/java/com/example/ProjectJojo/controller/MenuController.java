package com.example.ProjectJojo.controller;

import com.example.ProjectJojo.entity.Menu;
import com.example.ProjectJojo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> getMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return menus.stream().map(this::convertToDTO).toList();
    }

    private Menu convertToDTO(Menu menu) {
        Menu dto = new Menu();
        if (menu.getSubMenu() != null) {
            dto.setId(menu.getId());
            dto.setName(menu.getName());
            dto.setDescription(menu.getDescription());
            dto.setSubMenu(menu.getSubMenu().stream().map(this::convertToDTO).toList());
        }
        return dto;
    }

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    } 

    @DeleteMapping("/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return "Menu with id " + id + " has been deleted.";
    }
}
