package com.example.petproject.dto.request;

public class LoginRequest {
    private String email;
    private String password;
    public String name;
    public String fullname;

    public LoginRequest() {}

    public LoginRequest(String email, String password, String name, String fullname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
