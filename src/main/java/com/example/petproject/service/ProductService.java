package com.example.petproject.service;

import com.example.petproject.dto.request.ProductRequest;
import com.example.petproject.dto.respone.ProductResponse;
import com.example.petproject.entity.Product;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductByStatus(String status);
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(String category);
    List<ProductResponse> searchProducts(String name);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
