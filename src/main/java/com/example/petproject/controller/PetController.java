package com.example.petproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    @GetMapping("getPet")
    public String getPet() {
        return "Hello shopping online project";
    }
}
