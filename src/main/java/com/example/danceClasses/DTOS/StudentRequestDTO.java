package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class StudentRequestDTO {

    private String name;
    private Set<Course> courses;

    private Set<LocalDateTime> attendances;
    private Set<Payment> payments;

    private Set<Review> reviews;
    private LocalDate birthDate;


    public StudentRequestDTO(String name, Set<Course> courses,Set<LocalDateTime> attendances,Set<Payment> payments,Set<Review> reviews,LocalDate birthDate) {
        this.name = name;
        this.courses = courses;
        this.attendances = attendances;
        this.payments = payments;
        this.reviews = reviews;
        this.birthDate = birthDate;
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

    public Set<LocalDateTime> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<LocalDateTime> attendances) {
        this.attendances = attendances;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
