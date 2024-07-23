package com.example.danceClasses.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.danceClasses.DTOS.InstructorRequestDTO;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class InstructorService {
    private static final Logger logger = LoggerFactory.getLogger(InstructorService.class);

    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
        logger.info("InstructorService has been instantiated with InstructorRepository.");
    }

    @Transactional
    public Instructor addInstructor (InstructorRequestDTO instructorRequestDTO){
        logger.info("addInstructor called with name: {}", instructorRequestDTO.getName());
        Instructor newInstructor = new Instructor();
        newInstructor.setName(instructorRequestDTO.getName());
        newInstructor.setCourses(new HashSet<>());
        logger.info("Instructor {} has been added successfully.", newInstructor.getName());
        return instructorRepository.save(newInstructor);
    }

    @Transactional
    public Instructor findInstructorByName (String name){
        return instructorRepository.findInstructorByName(name);
    }
}
