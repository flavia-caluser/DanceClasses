package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Lesson;

public class LessonPaymentsRequestDTO {

    private String studentName;
    private String lessonName;

    public LessonPaymentsRequestDTO() {
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
