package com.example.studentmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double fee;
    private Double duration; // Duration in hours

    @OneToMany(mappedBy = "course" )
    private List<Student> students;


}
