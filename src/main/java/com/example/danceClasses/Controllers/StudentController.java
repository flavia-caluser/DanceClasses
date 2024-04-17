package com.example.danceClasses.Controllers;

import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.DTOS.StudentRequestDTO;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentRequestDTO));
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> addAttendance(@RequestParam LocalDateTime newAttendance, @RequestParam Long studentId){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addAttendance(newAttendance, studentId));
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> addPayment(@RequestBody PaymentRequestDTO paymentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addPayment(paymentRequestDTO));
    }

}
