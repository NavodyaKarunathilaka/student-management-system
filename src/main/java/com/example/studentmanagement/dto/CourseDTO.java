package com.example.studentmanagement.dto;

public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private Double fee;
    private Double duration;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, String description, Double fee, Double duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.duration = duration;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}