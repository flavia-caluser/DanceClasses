package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.LessonPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonPaymentRepository extends JpaRepository<LessonPayment, Long> {
    List<LessonPayment> findAllByStudentName(String name);
}
