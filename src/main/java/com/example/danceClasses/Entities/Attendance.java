package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference("student-attendance")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonBackReference("lesson-attendance")
    private Lesson lesson;

    @Column
    private LocalDateTime dateTime;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime date) {
        this.dateTime = date;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", student=" + student +
                ", lesson=" + lesson +
                ", date=" + dateTime +
                '}';
    }
}
