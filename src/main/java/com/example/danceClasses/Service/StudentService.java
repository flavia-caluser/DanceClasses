package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.DTOS.StudentRequestDTO;
import com.example.danceClasses.Entities.*;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
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
import java.util.stream.Collectors;

@Service
public class StudentService  {

    private final StudentRepository studentRepository;
    private final MailService mailService;

    @Autowired
    public StudentService(StudentRepository studentRepository,MailService mailService) {
        this.studentRepository = studentRepository;
        this.mailService= mailService;
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
        if(studentRepository.findStudentByName(name) == null)
            throw new ResourceNotFoundException("Studentul cu numele "+ name +" nu exista in baza de date");
        return studentRepository.findStudentByName(name);
    }

    @Transactional
    public List<String> getStudentCourses(String studentName){
        Student student = studentRepository.findStudentByName(studentName);
       return student.getCourses().stream()
                .map(Course::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    public Student changeStudentEmail(Long studentId, String newEmail){
        Student student = studentRepository.findStudentById(studentId);
        student.setEmailAddress(newEmail);
        return studentRepository.save(student);
    }

    public void deleteStudent (Long id){
        if(!studentRepository.existsById(id))
            throw new ResourceNotFoundException("Student not found with id "+ id);
        studentRepository.deleteById(id);
    }
}
