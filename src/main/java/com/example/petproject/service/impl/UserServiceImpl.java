package com.example.petproject.service.impl;

import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.RegisterRequest;
import com.example.petproject.dto.respone.AuthResponse;
import com.example.petproject.service.UserService;
import org.springframework.stereotype.Service;

// UserServiceImpl.java
@Service
public class UserServiceImpl implements UserService {

    @Override
    public RegisterRequest register(RegisterRequest registerRequest) {
        // ✅ Validate dữ liệu đầu vào (ví dụ đơn giản)
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (registerRequest.getName() == null || registerRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        RegisterRequest response = new RegisterRequest();
        response.setEmail(registerRequest.getEmail());
        response.setName(registerRequest.getName());
        return response;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // ✅ Validate dữ liệu
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }


        AuthResponse authRespone = new AuthResponse();
        authRespone.setEmail(loginRequest.getEmail());
        authRespone.setName(loginRequest.getName());
        authRespone.setFullname(loginRequest.getFullname());
        return authRespone;
    }
}

