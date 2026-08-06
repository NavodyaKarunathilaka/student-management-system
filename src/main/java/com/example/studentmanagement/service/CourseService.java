package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.request.CourseRequest;
import com.example.studentmanagement.dto.response.CourseResponse;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Create Course
    public CourseResponse createCourse(CourseRequest courseRequest) {

        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setFee(courseRequest.getFee());
        course.setDuration(courseRequest.getDuration());

        Course savedCourse = courseRepository.save(course);

        return toResponse(savedCourse);
    }

    // Get All Courses
    public List<CourseResponse> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        return toResponse(course);
    }

    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setFee(courseRequest.getFee());
        course.setDuration(courseRequest.getDuration());

        Course updatedCourse = courseRepository.save(course);

        return toResponse(updatedCourse);
    }

    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        courseRepository.delete(course);
    }

    private CourseResponse toResponse(Course course) {
        int enrolledStudentsCount = course.getStudents() == null ? 0 : course.getStudents().size();

        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getFee(),
                course.getDuration(),
                enrolledStudentsCount
        );
    }
}
