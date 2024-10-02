package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Instructor;

import java.time.LocalDateTime;
import java.util.Set;

public class CourseResponseDTO {

    private String name;
    private Set<String> instructorsNames;
    private Double lessonPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(String name, Set<String> instructorsNames, LocalDateTime startDate, LocalDateTime endDate,Double lessonPrice) {
        this.name = name;
        this.instructorsNames = instructorsNames;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lessonPrice = lessonPrice;
    }

    public Double getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Double lessonPrice) {
        this.lessonPrice = lessonPrice;
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
