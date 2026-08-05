package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.LoginRequest;
import com.example.studentmanagement.dto.LoginResponse;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Register User
    public void register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    // Login User
    public LoginResponse login(LoginRequest loginRequest) {

        System.out.println("========== LOGIN START ==========");
        System.out.println("Email Received : " + loginRequest.getEmail());

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User Found : " + user.getEmail());
        System.out.println("Stored Password : " + user.getPassword());
        System.out.println("Entered Password : " + loginRequest.getPassword());

        // Compare entered password with encrypted password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            System.out.println("Password does not match!");
            throw new RuntimeException("Invalid password");
        }

        System.out.println("Password matched successfully.");

        // Generate JWT Token
        String token = jwtService.generateToken(user.getEmail());

        System.out.println("Generated JWT : " + token);
        System.out.println("========== LOGIN END ==========");

        return new LoginResponse(
                token,
                user.getRole(),
                user.getEmail()
        );
    }
}