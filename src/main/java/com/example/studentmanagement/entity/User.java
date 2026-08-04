package com.example.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role; // e.g., "ADMIN", "USER"
    private String contactNum; // e.g., "ADMIN", "USER"

}
