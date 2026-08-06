package com.example.studentmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentRequest {

    @NotNull(message = "Course ID is mandatory")
    @Min(value = 1, message = "Course ID must be a positive number")
    private Long courseId;
}
