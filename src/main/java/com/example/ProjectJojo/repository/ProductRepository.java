package com.example.ProjectJojo.repository;

import com.example.ProjectJojo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}