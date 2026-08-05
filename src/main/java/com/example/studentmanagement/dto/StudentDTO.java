package com.example.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// This DTO represents a student with fields for id, name, email, age, and course ID.
// It includes validation annotations to ensure data integrity.
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @Min(value = 1, message = "Course ID must be a positive number")
    private Long courseId;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name, String email, Integer age, Long courseId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}