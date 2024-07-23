package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("SELECT p FROM Payment p JOIN p.lessonPaymentList l WHERE l IN :lessonPayments")
    List<Payment> findAllByLessonPaymentList(@Param("lessonPayments") List<LessonPayment> lessonPayments);
}
