package com.example.ProjectJojo.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter; 

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;   
    private String name;  
    private String description;   

    @OneToMany 
    @JoinColumn(name = "submenu")  
    private List<Menu> subMenus;

    public List<Menu> getSubmenu() {
        return subMenus;
    }

    public void setSubmenu(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }
}