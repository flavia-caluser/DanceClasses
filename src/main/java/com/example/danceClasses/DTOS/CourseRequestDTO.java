package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Instructor;


import java.util.Set;

public class CourseRequestDTO {

    private String name;
    private Set<String> instructorsNames;


    public CourseRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getInstructorsNames() {
        return instructorsNames;
    }

    public void setInstructorsNames(Set<String> instructorsNames) {
        this.instructorsNames = instructorsNames;
    }

    public CourseRequestDTO() {
    }
}
