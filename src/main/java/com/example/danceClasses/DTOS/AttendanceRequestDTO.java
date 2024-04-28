package com.example.danceClasses.DTOS;

import java.time.LocalDateTime;

public class AttendanceRequestDTO {

    private LocalDateTime date;

    private String studentName;
    private String lessonName;

    public AttendanceRequestDTO() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
