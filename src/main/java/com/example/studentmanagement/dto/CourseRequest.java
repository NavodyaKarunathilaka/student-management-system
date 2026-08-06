package com.example.studentmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Min(value = 0, message = "Fee must be a non-negative number")
    private Double fee;

    @Min(value = 1, message = "Duration must be at least 1 hour")
    private Double duration;
}
