package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.response.ApiResponse;
import com.example.studentmanagement.dto.request.LoginRequest;
import com.example.studentmanagement.dto.response.LoginResponse;
import com.example.studentmanagement.dto.request.RegisterRequest;
import com.example.studentmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
     * @param request the registration details
     * @return a response indicating the result of the registration
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest request) {

        userService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("User registered successfully"));
    }

    /**
     * Handles user login.
     *
     * @param loginRequest the login credentials
     * @return a response containing the JWT token if login is successful
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest loginRequest) {

        LoginResponse response = userService.login(loginRequest);

        return ResponseEntity.ok(ApiResponse.of("Login successful", response));
    }

}