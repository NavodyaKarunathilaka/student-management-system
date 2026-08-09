package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.response.ApiResponse;
import com.example.studentmanagement.dto.request.CourseRequest;
import com.example.studentmanagement.dto.response.CourseResponse;
import com.example.studentmanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // This controller manages course-related endpoints.
    // It provides endpoints for creating, retrieving, updating, and deleting courses.

    // Create Course
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        // Creates a new course.
        CourseResponse response = courseService.createCourse(courseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Course created successfully", response));
    }

    // Get All Courses
    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {
        // Retrieves all courses.
        List<CourseResponse> response = courseService.getAllCourses();
        return ResponseEntity.ok(ApiResponse.of("Courses retrieved successfully", response));
    }

    // Get Course By ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(@PathVariable Long id) {
        // Retrieves a course by its ID.
        CourseResponse response = courseService.getCourseById(id);
        return ResponseEntity.ok(ApiResponse.of("Course retrieved successfully", response));
    }

    // Update Course
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest courseRequest) {
        // Updates an existing course.
        CourseResponse response = courseService.updateCourse(id, courseRequest);
        return ResponseEntity.ok(ApiResponse.of("Course updated successfully", response));
    }

    // Delete Course
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        // Deletes a course by its ID.
        courseService.deleteCourse(id);

        return ResponseEntity.ok(ApiResponse.of("Course deleted successfully"));
    }

}
