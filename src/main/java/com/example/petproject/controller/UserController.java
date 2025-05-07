package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.RegisterRequest;
import com.example.petproject.dto.respone.AuthResponse;
import com.example.petproject.dto.respone.ProductRespone;
import com.example.petproject.entity.Product;
import com.example.petproject.entity.User;
import com.example.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(URLConstant.API_REGISTER)
    public Object register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @PostMapping(URLConstant.API_LOGIN)
    public Object login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
    @GetMapping(URLConstant.API_FINDALL)
    public List<User> getAllUsers() {
        // Gọi phương thức từ service để lấy danh sách người dùng
        return userService.getAllUsers();
    }

    @PostMapping(URLConstant.API_BLOCK_USER)
    public String blockUser(@PathVariable Long userId) {
        userService.blockUser(userId);
        return "Đã chặn user id: " + userId;
    }

}
