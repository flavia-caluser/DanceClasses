package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class StudentRequestDTO {

    private String name;
    private LocalDate birthDate;

    public StudentRequestDTO(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
