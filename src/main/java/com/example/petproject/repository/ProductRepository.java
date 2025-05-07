package com.example.petproject.repository;

import com.example.petproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByStatus(String status);
    List<Product> findAll();
}
