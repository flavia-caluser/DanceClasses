package com.example.danceClasses.DTOS;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LessonRequestDTO {

    private String name;
    private String courseName;

    private LocalDateTime dateAndTime;


    public LessonRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
