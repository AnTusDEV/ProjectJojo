package com.example.ProjectJojo.dto;

import java.util.List;

public class MenuResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<MenuResponseDTO> subMenus; // For hierarchical structure

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuResponseDTO> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuResponseDTO> subMenus) {
        this.subMenus = subMenus;
    }
}