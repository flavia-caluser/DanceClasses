package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Instructor;

import java.util.Set;

public class CourseResponseDTO {

    private String name;
    private Set<Instructor> instructors;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(String name, Set<Instructor> instructors) {
        this.name = name;
        this.instructors = instructors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }
}
