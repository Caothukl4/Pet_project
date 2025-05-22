package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.ProductRequest;
import com.example.petproject.dto.request.UserRegistrationRequest;
import com.example.petproject.dto.respone.ProductResponse;
import com.example.petproject.dto.respone.UserResponse;
import com.example.petproject.service.ProductService;
import com.example.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping(URLConstant.API_LOGIN)
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest request) {
        UserResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(URLConstant.API_GET_USER_ID)
    public ResponseEntity<UserResponse> getUserById(@RequestParam Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping(URLConstant.API_GET_USER_EMAIL)
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        UserResponse response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping(URLConstant.API_GET_USER)
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping(URLConstant.API_UPDATE_USER)
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRegistrationRequest request) {
        UserResponse updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(URLConstant.API_DELETE_USER)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    // Tạo sản phẩm mới
    @PostMapping(URLConstant.API_PRODUCT)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.ok(response);
    }

    // Lấy sản phẩm theo ID
    @GetMapping(URLConstant.API_GET_PRODUCT)
    public ResponseEntity<ProductResponse> getProductById(@RequestParam Long id) {
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(URLConstant.API_GET_PRODUCT_STATUS)
    public ResponseEntity<List<ProductResponse>> getProductStatus(@RequestParam String status) {
        List<ProductResponse> responses = productService.getProductByStatus(status);
        return ResponseEntity.ok(responses);
    }

    // Lấy tất cả sản phẩm
    @GetMapping(URLConstant.API_GET_ALL_PRODUCT)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Lấy sản phẩm theo danh mục
    @GetMapping(URLConstant.API_GET_PRODUCT_CATEGORY)
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@RequestParam String category) {
        List<ProductResponse> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    // Tìm kiếm sản phẩm theo từ khóa tên
    @GetMapping(URLConstant.API_GET_PRODUCT_NAME)
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String name) {
        List<ProductResponse> products = productService.searchProducts(name);
        return ResponseEntity.ok(products);
    }

    // Cập nhật sản phẩm theo ID
    @PutMapping(URLConstant.API_UPDATE_PRODUCT)
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    // Xóa sản phẩm theo ID
    @DeleteMapping(URLConstant.API_DELETE_PRODUCT)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
