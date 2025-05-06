package com.example.petproject.dto.respone;

public class AuthResponse {
    private String name;
    private String email;
    private String fullname;

    public AuthResponse(String name, String email, String fullname) {
        this.name = name;
        this.email = email;
        this.fullname = fullname;
    }

    public AuthResponse() {
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

}
