package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;
    @Column
    private String emailAddress;
    @ManyToMany(mappedBy = "instructors")
    @JsonBackReference
    private Set<Course> courses;


    public Instructor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        if(courses==null){
            courses=new HashSet<>();
        }
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

    public String getEmailAddress() {
        if(this.emailAddress ==null)
            this.emailAddress = new String("");
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", courses=" + courses +
                '}';
    }
}
