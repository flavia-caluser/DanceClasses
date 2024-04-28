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
    private CourseRepository courseRepository;

    private LessonRepository lessonRepository;
    private AttendanceRepository attendanceRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student addStudent(StudentRequestDTO studentRequestDTO){
        Student newStudent = new Student();
        newStudent.setName(studentRequestDTO.getName());
        newStudent.setBirthDate(studentRequestDTO.getBirthDate());
        return studentRepository.save(newStudent);
    }

    @Transactional
    public Attendance addAttendance(AttendanceRequestDTO attendanceRequestDTO){
        Student student = studentRepository.findStudentByName(attendanceRequestDTO.getStudentName());
        Lesson lesson = lessonRepository.findByName(attendanceRequestDTO.getLessonName());
        Attendance newAttendance = new Attendance();
        newAttendance.setStudent(student);
        newAttendance.setLesson(lesson);
        newAttendance.setDate(attendanceRequestDTO.getDate());
        student.getAttendances().add(newAttendance);
        studentRepository.save(student);
        lessonRepository.save(lesson);
        return attendanceRepository.save(newAttendance);
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
