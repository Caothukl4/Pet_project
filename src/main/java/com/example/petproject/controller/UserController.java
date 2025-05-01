package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.RegisterRequest;
import com.example.petproject.dto.respone.AuthRespone;
import com.example.petproject.dto.respone.ProductRespone;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @PostMapping(URLConstant.API_REGISTER)
    public Object register(@RequestBody RegisterRequest registerRequest) {
        RegisterRequest r = new RegisterRequest();
        r.setEmail(registerRequest.getEmail());
        r.setName(registerRequest.getName());
        return r;
    }

    @PostMapping(URLConstant.API_LOGIN)
    public Object login(@RequestBody LoginRequest loginRequest) {
        AuthRespone authRespone = new AuthRespone();
        authRespone.setEmail(loginRequest.getEmail());
        authRespone.setPassword(loginRequest.getPassword());
        return authRespone;
    }

}
