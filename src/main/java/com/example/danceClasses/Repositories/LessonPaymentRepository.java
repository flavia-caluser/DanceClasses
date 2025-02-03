package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonPaymentRepository extends JpaRepository<LessonPayment, Long> {
    List<LessonPayment> findAllByStudentName(String name);
    LessonPayment findByLessonAndPaymentAndStudent(Lesson lesson, Payment payment, Student student);

}
