package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.LoginRequest;
import com.example.studentmanagement.dto.LoginResponse;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles user registration.
     *
     * @param user the user to register
     * @return a response indicating the result of the registration
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        userService.register(user);

        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * Handles user login.
     *
     * @param loginRequest the login credentials
     * @return a response containing the JWT token if login is successful
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {

        LoginResponse response = userService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

}