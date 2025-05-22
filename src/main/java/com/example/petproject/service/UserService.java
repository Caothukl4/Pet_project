package com.example.petproject.service;

import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.UserRegistrationRequest;
import com.example.petproject.dto.respone.AuthResponse;
import com.example.petproject.dto.respone.UserResponse;
import com.example.petproject.entity.Product;
import com.example.petproject.entity.User;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest request);
    UserResponse loginUser(LoginRequest request);
    UserResponse getUserById(Long id);
    UserResponse getUserByEmail(String email);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRegistrationRequest request);
    void deleteUser(Long id);
}
