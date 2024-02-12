package com.example.danceClasses.Entities;

import jakarta.persistence.*;

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

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    @ElementCollection
    private List<LocalDateTime> attendences;

    //oneToMany cu payment
    //oneToMany cu Review
}
