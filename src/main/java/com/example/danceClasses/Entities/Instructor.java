package com.example.danceClasses.Entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;
    @ManyToMany(mappedBy = "Instructori")
    private Set<Course> courses;

    public Instructor(String name, Set<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Instructor() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
