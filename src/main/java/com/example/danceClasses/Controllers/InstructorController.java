package com.example.danceClasses.Controllers;

import com.example.danceClasses.DTOS.InstructorRequestDTO;
import com.example.danceClasses.DTOS.StudentRequestDTO;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Instructor> addInstructor(@RequestBody InstructorRequestDTO instructorRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.addInstructor(instructorRequestDTO));
    }

    @GetMapping( "/{name}")
    public ResponseEntity<Instructor> findInstructorByName(@PathVariable String name){
        return ResponseEntity.ok(instructorService.findInstructorByName(name));
    }
}
