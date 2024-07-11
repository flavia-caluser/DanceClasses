package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;

    //TODO
    //de afaugat un localdatetime cu data si ora lectiei *

    @Column
    private LocalDateTime dateAndTime;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference("lesson-course")
    private Course course;

    @OneToMany(mappedBy = "lesson",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("lesson-attendance")
    private Set<Attendance> attendances;

    @OneToMany(mappedBy = "lesson",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("lesson-lessonPayment")
    private List<LessonPayment> LessonPaymentList;

    public Lesson() {
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public List<LessonPayment> getLessonPaymentList() {
        return LessonPaymentList;
    }

    public void setLessonPaymentList(List<LessonPayment> lessonPaymentList) {
        LessonPaymentList = lessonPaymentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Attendance> getAttendances() {
        if(attendances==null)
            attendances=new HashSet<>();
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", attendances=" + attendances +
                '}';
    }
}
