package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.ApiResponse;
import com.example.studentmanagement.dto.EnrollmentRequest;
import com.example.studentmanagement.dto.EnrollmentResponse;
import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Retrieves all students.
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents() {
        List<StudentResponse> response = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.of("Students retrieved successfully", response));
    }

    // Retrieves a student by their ID.
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(@PathVariable Long id) {
        StudentResponse response = studentService.getStudentById(id);
        return ResponseEntity.ok(ApiResponse.of("Student retrieved successfully", response));
    }

    // Updates a student's profile fields (name/email/age). Course enrollment is managed via /enroll.
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest studentRequest) {

        StudentResponse response = studentService.updateStudent(id, studentRequest);
        return ResponseEntity.ok(ApiResponse.of("Student updated successfully", response));
    }

    // Enrolls an existing student into a course, replacing any current enrollment.
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/enroll")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> enrollStudent(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentRequest enrollmentRequest) {

        EnrollmentResponse response = studentService.enrollStudent(id, enrollmentRequest);
        return ResponseEntity.ok(ApiResponse.of("Student enrolled successfully", response));
    }

    // Deletes a student by their ID.
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(ApiResponse.of("Student deleted successfully"));
    }
}
