package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;

    @ElementCollection
    private List<LocalDateTime> attendences;

    private LocalDate birthDate;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    @OneToMany(mappedBy = "student",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("payment-student")
    private List<Payment> payments;

    @OneToMany(mappedBy = "student",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("review-student")
    private List<Review> reviews;

    public Student() {
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public List<LocalDateTime> getAttendences() {
        return attendences;
    }

    public void setAttendences(List<LocalDateTime> attendences) {
        this.attendences = attendences;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                ", attendences=" + attendences +
                ", payments=" + payments +
                ", reviews=" + reviews +
                '}';
    }

}
