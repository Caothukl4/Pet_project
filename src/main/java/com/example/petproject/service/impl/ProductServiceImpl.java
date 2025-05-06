package com.example.petproject.service.impl;

import com.example.petproject.dto.respone.ProductRespone;
import com.example.petproject.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service  // Annotation để Spring tự động quét bean này
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductRespone> getProducts(String status) {
        // Mock data
        List<ProductRespone> products = Arrays.asList(
                new ProductRespone(1, "Sản phẩm A", 100000, "Đang bán"),
                new ProductRespone(2, "Sản phẩm B", 200000, "Đang bán"),
                new ProductRespone(3, "Sản phẩm C", 150000, "Hết hàng")
        );

        if (status != null && !status.isEmpty()) {
            return products.stream()
                    .filter(p -> p.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }
        return products;
    }
}

