package com.example.petproject.controller;


import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.UserRegistrationRequest;
import com.example.petproject.dto.respone.AuthResponse;
import com.example.petproject.dto.respone.UserResponse;
import com.example.petproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(URLConstant.API_REGISTER)
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(URLConstant.API_LOGIN)
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest request) {
        UserResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

}
