package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference("student-attendance")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonBackReference("lesson-attendance")
    private Lesson lesson;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", student=" + student +
                ", lesson=" + lesson +
                '}';
    }
}
