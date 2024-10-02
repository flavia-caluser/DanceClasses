package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Instructor;


import java.time.LocalDateTime;
import java.util.Set;

public class CourseRequestDTO {

    private String name;
    private Set<String> instructorsNames;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double lessonPrice;

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

    public Double getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Double lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    public CourseRequestDTO() {
    }
}
