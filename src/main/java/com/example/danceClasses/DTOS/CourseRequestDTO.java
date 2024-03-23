package com.example.danceClasses.DTOS;

public class CourseRequestDTO {

    private String name;

    public CourseRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseRequestDTO() {
    }
}
