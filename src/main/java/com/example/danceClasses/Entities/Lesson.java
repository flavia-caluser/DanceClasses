package com.example.danceClasses.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    //nume
    //data
    //ManyToOne cu course

}
