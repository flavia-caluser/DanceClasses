package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Instructor;

import java.time.LocalDateTime;
import java.util.Set;

public class CourseResponseDTO {

    private String name;
    private Set<Instructor> instructors;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
