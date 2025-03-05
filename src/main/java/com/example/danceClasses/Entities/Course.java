package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    //TODO: sa adaug un atribut lessonPrice

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private Double lessonPrice;

    @ManyToMany
    @JoinTable(
            name = "instructor_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name="instructor_id")
    )
    @JsonManagedReference
    private Set<Instructor> instructors;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    @JsonManagedReference("student-course")
    private Set<Student> students;

    @OneToMany(mappedBy = "course",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("lesson-course")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("review-course")
    private List<Review> reviews;

    public Course() {
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

    public Set<Instructor> getInstructors() {
        if(instructors==null)
            instructors=new HashSet<>();
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public Set<Student> getStudents() {
        if(students==null)
            students=new HashSet<>();
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        if(lessons==null)
            lessons=new ArrayList<>();
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Double getLessonPrice() {
        if(lessonPrice==null)
            lessonPrice=0.00;
        return lessonPrice;
    }

    public void setLessonPrice(Double lessonPrice) {
        this.lessonPrice = lessonPrice;
    }
    //    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                ", instructors=" + instructors +
//                ", students=" + students +
//                ", lessons=" + lessons +
//                ", reviews=" + reviews +
//                '}';
//    }
}
