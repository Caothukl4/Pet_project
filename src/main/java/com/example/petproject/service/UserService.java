package com.example.petproject.service;

import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.RegisterRequest;
import com.example.petproject.dto.respone.AuthResponse;
import com.example.petproject.entity.Product;
import com.example.petproject.entity.User;

import java.util.List;

public interface UserService {
    User register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
    List<User> getAllUsers();

    // Admin functions
    void blockUser(Long userId);

}
