package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.respone.ProductRespone;
import com.example.petproject.entity.Product;
import com.example.petproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(URLConstant.API_PRODUCT)
    public List<Product> getAllAvailableProducts() {
        return productService.getAllAvailableProducts();
    }

    @PostMapping(URLConstant.API_ADD_PRODUCT)
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping(URLConstant.API_UPDATE_PRODUCT)
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping(URLConstant.API_DELETE_PRODUCT)
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "Đã xóa sản phẩm id: " + productId;
    }

    @GetMapping(URLConstant.API_PRODUCTS)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
