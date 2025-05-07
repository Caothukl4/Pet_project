package com.example.petproject.service;

import com.example.petproject.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllAvailableProducts();
    Product addProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    List<Product> getAllProducts();
}
