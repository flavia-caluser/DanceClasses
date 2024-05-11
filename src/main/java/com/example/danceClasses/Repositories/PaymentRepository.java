package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findAllByStudentId(Long id);
}
