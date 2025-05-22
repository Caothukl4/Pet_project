package com.example.petproject.repository;

import com.example.petproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByStatus(String status);
    Optional<Product> findById(Long id);
    List<Product> findByCategoryIgnoreCase(String category);
}
