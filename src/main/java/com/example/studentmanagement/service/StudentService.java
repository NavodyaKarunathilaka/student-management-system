package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Student;
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

    // Create Student
    public StudentDTO createStudent(StudentDTO studentDTO) {

        Course course = courseRepository.findById(studentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setCourse(course);

        Student savedStudent = studentRepository.save(student);

        return new StudentDTO(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail(),
                savedStudent.getAge(),
                savedStudent.getCourse().getId()
        );
    }

    // Get All Students
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getAge(),
                        student.getCourse().getId()
                ))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAge(),
                student.getCourse().getId()
        );
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(studentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setCourse(course);

        Student updatedStudent = studentRepository.save(student);

        return new StudentDTO(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail(),
                updatedStudent.getAge(),
                updatedStudent.getCourse().getId()
        );
    }

    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);
    }

}