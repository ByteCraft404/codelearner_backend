package com.codelearner.dto;

public class LoginResponse {
    private String id;
    private String email;
    private String fullName;

    public LoginResponse(String id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }
}
