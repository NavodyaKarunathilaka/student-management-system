package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.LoginRequest;
import com.example.studentmanagement.dto.LoginResponse;
import com.example.studentmanagement.dto.RegisterRequest;
import com.example.studentmanagement.entity.Role;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.exception.EmailAlreadyExistsException;
import com.example.studentmanagement.exception.InvalidCredentialsException;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final StudentService studentService;

    public UserService(UserRepository userRepository, StudentRepository studentRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       StudentService studentService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.studentService = studentService;
    }

    // Register User
    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setContactNum(request.getContactNum());
        // Every self-registration is a plain USER; ADMIN accounts are promoted manually.
        user.setRole(Role.USER);

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        // Every self-registered account is a student; the academic profile is created
        // without a course - the student enrolls into one afterward via /enroll.
        studentService.createStudentProfile(savedUser);
    }

    // Login User
    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // Compare entered password with encrypted password
        // Same error message as the "user not found" case above, to avoid leaking which emails are registered.
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Generate JWT Token
        String token = jwtService.generateToken(user.getEmail(), user.getRole());

        // ADMIN accounts are promoted manually and have no linked student profile.
        Long studentId = studentRepository.findByUserId(user.getId())
                .map(Student::getId)
                .orElse(null);

        return new LoginResponse(
                token,
                user.getRole().name(),
                user.getEmail(),
                studentId
        );
    }
}