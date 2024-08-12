package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Course;

import java.util.Set;

public class InstructorRequestDTO {

    private String name;

    private String emailAddress;

    public InstructorRequestDTO(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public InstructorRequestDTO() {
    }
}
