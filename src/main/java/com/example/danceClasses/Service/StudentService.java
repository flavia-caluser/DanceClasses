package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.StudentRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        newStudent.setCourses(new HashSet<>());
        newStudent.setAttendences(new HashSet<>());
        newStudent.setPayments(new HashSet<>());
        newStudent.setReviews(new ArrayList<>());
        return studentRepository.save(newStudent);
    }

}
