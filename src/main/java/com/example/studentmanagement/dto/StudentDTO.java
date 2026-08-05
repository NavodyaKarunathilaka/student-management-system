package com.example.studentmanagement.dto;

public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Long courseId;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name, String email, Integer age, Long courseId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.courseId = courseId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}