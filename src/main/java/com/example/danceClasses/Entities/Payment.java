package com.example.danceClasses.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    //data
    //ManyToOne cu Student
    //ManyToOne cu Course
    //Enum Card/Cash
}
