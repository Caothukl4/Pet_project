package com.example.petproject.service;

import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.RegisterRequest;
import com.example.petproject.dto.respone.AuthResponse;

// UserService.java
public interface UserService {
    RegisterRequest register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}

