package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.LessonPaymentsRequestDTO;
import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Mapper.LessonPaymentMapper;
import com.example.danceClasses.Repositories.LessonPaymentRepository;
import com.example.danceClasses.Repositories.PaymentRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import com.example.danceClasses.Repositories.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private LessonPaymentRepository lessonPaymentRepository;

    private LessonPaymentMapper lessonPaymentMapper;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment addPayment(PaymentRequestDTO paymentRequestDTO) {
        Payment newPayment = new Payment();
        newPayment.setDate(paymentRequestDTO.getDate());
        newPayment.setMethod(paymentRequestDTO.getPaymentMethod());
        List<LessonPayment> list = paymentRequestDTO.getLessonPaymentsRequestDTOList().stream()
                 .map(DTO-> lessonPaymentMapper.fromDTOToLessonPayment(DTO))
                 .toList();
        newPayment.setLessonPaymentList(list);
        return paymentRepository.save(newPayment);
    }
    public List<Payment> getAllByStudentName(String studentName){
        List<LessonPayment> lessonPaymentList = lessonPaymentRepository.findAllByStudentName(studentName);
        List<Payment> allPayments = paymentRepository.findAllByLessonPaymentList(lessonPaymentList);
        return allPayments;
    }

    public Payment getLastPaymentByStudentName(String studentName){
        List<Payment> allPayments = getAllByStudentName(studentName);
        return allPayments.getLast();
    }

    }
