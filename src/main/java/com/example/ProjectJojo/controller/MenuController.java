package com.example.ProjectJojo.controller;

import com.example.ProjectJojo.entity.Menu;
import com.example.ProjectJojo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> getMenus() {
        List<Menu> menus = fetchMenusFromDatabase();
        return convertToDTOList(menus);
        
    }

    private Menu convertToDTO(Menu menu) {
        Menu dto = new Menu();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        if (menu.getSubmenu() != null) {
            System.out.println(dto.getSubMenus());
            dto.setSubMenus(menu.getSubmenu().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public List<Menu> convertToDTOList(List<Menu> menus) {
        return menus.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<Menu> fetchMenusFromDatabase() {
        // Mock or actual database call
        return List.of(); // Replace with actual logic
    }

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable Long id, @RequestBody Menu menu) {
        return menuService.updateMenu(id, menu);
    }

    @DeleteMapping("/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return "Menu with id " + id + " has been deleted.";
    }
}
