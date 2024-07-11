package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class LessonPayment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonBackReference("lesson-lessonPayment")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    @JsonBackReference("payment-lessonPayment")
    private Payment payment;

    public LessonPayment() {
    }

    public LessonPayment(Long id, Student student, Lesson lesson, Payment payment) {
        this.id = id;
        this.student = student;
        this.lesson = lesson;
        this.payment = payment;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "LessonPayment{" +
                "id=" + id +
                ", student=" + student +
                ", lesson=" + lesson +
                ", payment=" + payment +
                '}';
    }
}
