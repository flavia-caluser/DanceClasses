package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.PaymentRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import com.example.danceClasses.Repositories.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

//    @Transactional
//    public Payment addPayment(PaymentRequestDTO paymentRequestDTO){
//        Student student = studentRepository.findStudentByName(paymentRequestDTO.getStudentName());
//        Payment newPayment = new Payment();
//        newPayment.setStudent(student);
//        newPayment.setCourse(courseRepository.findCourseByName(paymentRequestDTO.getCourseName()));
//        newPayment.setDate(paymentRequestDTO.getDate());
//        newPayment.setMethod(paymentRequestDTO.getPaymentMethod());
//        student.getPayments().add(newPayment);
//        studentRepository.save(student);
//        return paymentRepository.save(newPayment);
//    }

    public List<Payment> getAllByStudentId(Long studentId){
        return paymentRepository.findAllByStudentId(studentId);
    }

    public Payment getLastPaymentByStudentId(Long studentId){
        List<Payment> allPayments = paymentRepository.findAllByStudentId(studentId);
        return allPayments.getLast();
    }
}
