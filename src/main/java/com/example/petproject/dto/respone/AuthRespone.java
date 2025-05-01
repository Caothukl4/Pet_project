package com.example.petproject.dto.respone;

public class AuthRespone {
    private String name;
    private String password;
    private String email;
    private String fullname;

    public AuthRespone(String name, String email,String password, String fullname) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public AuthRespone() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
