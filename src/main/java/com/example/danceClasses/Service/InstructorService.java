package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.InstructorRequestDTO;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class InstructorService {

    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public Instructor addInstructor (InstructorRequestDTO instructorRequestDTO){
        Instructor newInstructor = new Instructor();
        newInstructor.setName(instructorRequestDTO.getName());
        newInstructor.setCourses(new HashSet<>());
        return instructorRepository.save(newInstructor);
    }
}
