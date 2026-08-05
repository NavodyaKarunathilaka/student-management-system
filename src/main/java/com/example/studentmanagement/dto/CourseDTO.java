package com.example.studentmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// This DTO represents a course with fields for id, name, description, fee, and duration.
// It includes validation annotations to ensure data integrity.
public class CourseDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Min(value = 0, message = "Fee must be a non-negative number")
    private Double fee;

    @Min(value = 1, message = "Duration must be at least 1 hour")
    private Double duration;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, String description, Double fee, Double duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.duration = duration;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}