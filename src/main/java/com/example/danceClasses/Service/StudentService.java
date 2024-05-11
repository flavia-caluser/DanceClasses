package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.AttendanceRequestDTO;
import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.DTOS.StudentRequestDTO;
import com.example.danceClasses.Entities.*;
import com.example.danceClasses.Repositories.AttendanceRepository;
import com.example.danceClasses.Repositories.CourseRepository;
import com.example.danceClasses.Repositories.LessonRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService  {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student addStudent(StudentRequestDTO studentRequestDTO){
        Student newStudent = new Student();
        newStudent.setName(studentRequestDTO.getName());
        newStudent.setBirthDate(studentRequestDTO.getBirthDate());
        newStudent.setEmailAddress(studentRequestDTO.getEmailAddress());
        return studentRepository.save(newStudent);
    }
    public Student getStudentByName(String name){
        Student student = studentRepository.findStudentByName(name);
        return student;
    }



}
