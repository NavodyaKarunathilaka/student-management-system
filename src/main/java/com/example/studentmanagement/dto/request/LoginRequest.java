package com.example.studentmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;

// This DTO represents the request payload for a login operation.
// It contains fields for email and password.
@Getter
@Setter
public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest() {
    }

}