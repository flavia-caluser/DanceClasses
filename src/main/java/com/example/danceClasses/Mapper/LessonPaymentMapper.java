package com.example.danceClasses.Mapper;

import com.example.danceClasses.DTOS.LessonPaymentRequestDTO;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
import com.example.danceClasses.Repositories.LessonPaymentRepository;
import com.example.danceClasses.Repositories.LessonRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LessonPaymentMapper {
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
        payment.getLessonPaymentList().add(result);
        result.setLesson(lesson);
        result.setStudent(student);
        //lessonPaymentRepository.save(result);
        return result;

    }
}
