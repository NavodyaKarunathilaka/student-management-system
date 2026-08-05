package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.entity.Course;
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
    public CourseDTO createCourse(CourseDTO courseDTO) {

        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setFee(courseDTO.getFee());
        course.setDuration(courseDTO.getDuration());

        Course savedCourse = courseRepository.save(course);

        return new CourseDTO(
                savedCourse.getId(),
                savedCourse.getName(),
                savedCourse.getDescription(),
                savedCourse.getFee(),
                savedCourse.getDuration()
        );
    }

    // Get All Courses
    public List<CourseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getName(),
                        course.getDescription(),
                        course.getFee(),
                        course.getDuration()
                ))
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getFee(),
                course.getDuration()
        );
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setFee(courseDTO.getFee());
        course.setDuration(courseDTO.getDuration());

        Course updatedCourse = courseRepository.save(course);

        return new CourseDTO(
                updatedCourse.getId(),
                updatedCourse.getName(),
                updatedCourse.getDescription(),
                updatedCourse.getFee(),
                updatedCourse.getDuration()
        );
    }

    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courseRepository.delete(course);
    }
}