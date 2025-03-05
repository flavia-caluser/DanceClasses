package com.example.danceClasses.Service;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.danceClasses.DTOS.InstructorRequestDTO;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
public class InstructorService {
    //private static final Logger logger = LoggerFactory.getLogger(InstructorService.class);

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
        newInstructor.setEmailAddress(instructorRequestDTO.getEmailAddress());
        return instructorRepository.save(newInstructor);
    }

    @Transactional
    public Instructor findInstructorByName (String name){
        return instructorRepository.findInstructorByName(name);
    }

    public void deleteInstructor(Long id){
        if(!instructorRepository.existsById(id))
            throw new ResourceNotFoundException("Instructor not found with id " + id);
        instructorRepository.deleteById(id);
    }

    @Transactional
    public Instructor changeInstructorEmail(Long instructorId, String newEmail){
        Optional<Instructor>  optional = instructorRepository.findById(instructorId);
        Instructor instructor= optional.get();
        instructor.setEmailAddress(newEmail);
        return instructorRepository.save(instructor);
    }
}
