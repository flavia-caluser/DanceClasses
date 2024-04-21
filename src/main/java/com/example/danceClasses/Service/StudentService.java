package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.DTOS.StudentRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.CourseRepository;
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
    private CourseRepository courseRepository;

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

    @Transactional
    public Boolean addAttendance(LocalDateTime newAttendance, Long studentId){
        Student student = studentRepository.findStudentById(studentId);
        int numberOfAttendancesBefore = student.getAttendences().size();
        student.getAttendences().add(newAttendance);
        if(student.getAttendences().size()<=numberOfAttendancesBefore){
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean addPayment(PaymentRequestDTO paymentRequestDTO){
        Student student = studentRepository.findStudentByName(paymentRequestDTO.getStudentName());
        int numberOfPaymentsBefore = student.getPayments().size();
        Payment newPayment = new Payment();
        newPayment.setStudent(student);
        newPayment.setCourse(courseRepository.findCourseByName(paymentRequestDTO.getCourseName()));
        newPayment.setDate(paymentRequestDTO.getDate());
        newPayment.setMethod(paymentRequestDTO.getPaymentMethod());
        student.getPayments().add(newPayment);
        if (student.getPayments().size()<=numberOfPaymentsBefore){
            return false;
        }
        return true;
    }

}
