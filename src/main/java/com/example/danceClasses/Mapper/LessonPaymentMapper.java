package com.example.danceClasses.Mapper;

import jakarta.persistence.EntityManager;

import com.example.danceClasses.DTOS.LessonPaymentRequestDTO;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
import com.example.danceClasses.Repositories.LessonPaymentRepository;
import com.example.danceClasses.Repositories.LessonRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import com.zaxxer.hikari.util.FastList;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LessonPaymentMapper {
    @PersistenceContext
    private EntityManager entityManager;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final LessonPaymentRepository lessonPaymentRepository;

    @Autowired
    public LessonPaymentMapper(LessonRepository lessonRepository, StudentRepository studentRepository, LessonPaymentRepository lessonPaymentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.lessonPaymentRepository = lessonPaymentRepository;
    }

    @Transactional
    public LessonPayment fromLessonNameToLessonPayment(String lessonName, Payment payment, String studentName) {

        Lesson lesson = lessonRepository.findByName(lessonName);
        Student student = studentRepository.findStudentByName(studentName);
        LessonPayment result = new LessonPayment();
        result.setPayment(payment);
        result.setLesson(lesson);
        result.setStudent(student);
        studentRepository.save(student);
        lessonRepository.save(lesson);
        System.out.println("Lesson managed? " + entityManager.contains(result.getLesson()));
        System.out.println("Student managed? " + entityManager.contains(result.getStudent()));
        System.out.println("Payment managed? " + entityManager.contains(result.getPayment()));
        lessonPaymentRepository.save(result);
        return result;

    }
}
