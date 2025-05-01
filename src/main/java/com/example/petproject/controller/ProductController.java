package com.example.petproject.controller;


import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.respone.ProductRespone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @GetMapping(URLConstant.API_PRODUCT)
    public List<ProductRespone> getProduct(@RequestParam(required = false) String status) {
        // Mock data
        List<ProductRespone> products = Arrays.asList(
                new ProductRespone(1, "Sản phẩm A", 100000, "Đang bán"),
                new ProductRespone(2, "Sản phẩm B", 200000, "Đang bán"),
                new ProductRespone(3, "Sản phẩm C", 150000, "Hết hàng")
        );

        // Nếu có status thì lọc
        if (status != null && !status.isEmpty()) {
            return products.stream()
                    .filter(p -> p.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }

        // Nếu không có status thì trả tất cả
        return products;
    }
}

