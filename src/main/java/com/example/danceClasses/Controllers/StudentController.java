package com.example.danceClasses.Controllers;

import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.DTOS.StudentRequestDTO;
import com.example.danceClasses.Entities.Attendance;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentRequestDTO));
    }
    @GetMapping("/{studentName}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String studentName){
        return ResponseEntity.ok(studentService.getStudentByName(studentName));
    }
    @GetMapping("/courses/{studentName}")
    public ResponseEntity<List<String>> getStudentCourses(@PathVariable String studentName){
        return ResponseEntity.ok(studentService.getStudentCourses(studentName));
    }

    @PutMapping("/changeEmail/{studentId}")
    public ResponseEntity<Student> changeStudentEmail(@PathVariable Long studentId, @RequestBody String newEmail){
        return ResponseEntity.ok(studentService.changeStudentEmail(studentId, newEmail));
    }




}
