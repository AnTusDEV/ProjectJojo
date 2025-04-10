package com.example.ProjectJojo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjectJojo.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}