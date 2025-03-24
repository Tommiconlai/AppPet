package com.example.apppet;

public class RegisterResponse {

    String password;
    String email;
    private String message;
    private long userId;

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getPassword() {
        return password;
    }
}