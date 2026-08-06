package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.EnrollmentRequest;
import com.example.studentmanagement.dto.EnrollmentResponse;
import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.exception.DuplicateEnrollmentException;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Creates the Student profile linked to a newly registered User. No course yet - enroll separately.
    public void createStudentProfile(User user) {

        Student student = new Student();
        student.setName(user.getName());
        student.setEmail(user.getEmail());
        student.setUser(user);

        studentRepository.save(student);
    }

    // Get All Students
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        return toResponse(student);
    }

    // Updates a student's profile fields (name/email/age). Course enrollment is managed via enrollStudent.
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());

        Student updatedStudent = studentRepository.save(student);

        return toResponse(updatedStudent);
    }

    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        studentRepository.delete(student);
    }

    // Enroll an existing student into a course, replacing any current enrollment.
    public EnrollmentResponse enrollStudent(Long studentId, EnrollmentRequest enrollmentRequest) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(enrollmentRequest.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + enrollmentRequest.getCourseId()));

        if (student.getCourse() != null && student.getCourse().getId().equals(course.getId())) {
            throw new DuplicateEnrollmentException(
                    "Student " + studentId + " is already enrolled in course " + course.getId());
        }

        student.setCourse(course);
        Student updatedStudent = studentRepository.save(student);

        return new EnrollmentResponse(
                updatedStudent.getId(),
                updatedStudent.getName(),
                course.getId(),
                course.getName(),
                course.getFee()
        );
    }

    private StudentResponse toResponse(Student student) {

        Course course = student.getCourse();
        User user = student.getUser();

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAge(),
                course == null ? null : course.getId(),
                course == null ? null : course.getName(),
                user == null ? null : user.getId()
        );
    }

}
