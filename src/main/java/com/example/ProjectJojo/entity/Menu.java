package com.example.ProjectJojo.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter; 

@Entity
@Table(name ="menu")
@Getter
@Setter
public class Menu { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String name; 
    private String description; 

    // @OneToMany(mappedBy = "parentMenu", cascade = CascadeType.ALL)
    private List<Menu> subMenus;

    // @ManyToOne
    // @JoinColumn(name = "submenu")
    private Menu parentMenu;
}