package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
import com.example.danceClasses.Mapper.LessonPaymentMapper;
import com.example.danceClasses.Repositories.LessonPaymentRepository;
import com.example.danceClasses.Repositories.PaymentRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import com.example.danceClasses.Repositories.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final LessonPaymentRepository lessonPaymentRepository;
    private final LessonPaymentMapper lessonPaymentMapper;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, LessonPaymentRepository lessonPaymentRepository, LessonPaymentMapper lessonPaymentMapper) {
        this.paymentRepository = paymentRepository;
        this.lessonPaymentRepository = lessonPaymentRepository;
        this.lessonPaymentMapper = lessonPaymentMapper;
    }

    @Transactional
    public Payment addPayment(PaymentRequestDTO paymentRequestDTO) {
        Payment newPayment = new Payment();
        newPayment.setDate(paymentRequestDTO.getDate());
        newPayment.setMethod(paymentRequestDTO.getPaymentMethod());
        List<LessonPayment> list = paymentRequestDTO.getLessonPaymentRequestDTOList().stream()
                .map(lessonPaymentMapper::fromDTOToLessonPayment)
                .toList();
        list.forEach(lessonPayment -> lessonPayment.setPayment(newPayment));
        newPayment.setLessonPaymentList(list);
        lessonPaymentRepository.saveAll(list);
        return paymentRepository.save(newPayment);
    }

    public List<Payment> getAllByStudentName(String studentName) {
        List<LessonPayment> lessonPaymentList = lessonPaymentRepository.findAllByStudentName(studentName);
        List<Payment> allPayments = paymentRepository.findAllByLessonPaymentList(lessonPaymentList);
        return allPayments;
    }

    public Payment getLastPaymentByStudentName(String studentName) {
        List<Payment> allPayments = getAllByStudentName(studentName);
        return allPayments.getLast();
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id))
            throw new ResourceNotFoundException("Payment not found with id " + id);
        paymentRepository.deleteById(id);
    }
}
