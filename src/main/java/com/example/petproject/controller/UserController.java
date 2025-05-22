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
}
