package com.example.danceClasses.DTOS;

import java.time.LocalDate;

public class LessonRequestDTO {

    private String name;
    private String courseName;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
