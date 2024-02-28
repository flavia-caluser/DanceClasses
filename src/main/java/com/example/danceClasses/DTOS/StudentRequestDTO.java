package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Course;

import java.util.Set;

public class StudentRequestDTO {

    private String name;
    private Set<Course> courses;


    public StudentRequestDTO(String name, Set<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
