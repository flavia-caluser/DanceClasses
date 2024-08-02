package com.example.danceClasses.DTOS;

import java.time.LocalDateTime;

public class AttendanceRequestDTO {
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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
