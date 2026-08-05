package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.service.CourseService;
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
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        // Creates a new course.
        return ResponseEntity.ok(courseService.createCourse(courseDTO));
    }

    // Get All Courses
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        // Retrieves all courses.
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // Get Course By ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        // Retrieves a course by its ID.
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // Update Course
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseDTO courseDTO) {
        // Updates an existing course.
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }

    // Delete Course
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        // Deletes a course by its ID.
        courseService.deleteCourse(id);

        return ResponseEntity.ok("Course deleted successfully");
    }

}