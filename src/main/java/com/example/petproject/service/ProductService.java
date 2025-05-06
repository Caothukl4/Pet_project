package com.example.petproject.service;

import com.example.petproject.dto.respone.ProductRespone;

import java.util.List;

public interface ProductService {
    List<ProductRespone> getProducts(String status);
}

