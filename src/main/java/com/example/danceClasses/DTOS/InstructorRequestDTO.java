package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Course;

import java.util.Set;

public class InstructorRequestDTO {

    private String name;

    public InstructorRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
