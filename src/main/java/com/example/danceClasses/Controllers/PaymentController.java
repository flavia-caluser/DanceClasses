package com.example.danceClasses.Controllers;

import com.example.danceClasses.DTOS.LessonPaymentRequestDTO;
import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addPayment/{studentName}")
    public ResponseEntity<Payment> addPayment(@PathVariable String studentName, @RequestBody PaymentRequestDTO paymentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.addPayment(studentName, paymentRequestDTO));
    }

    @GetMapping("/all/{studentName}")
    public ResponseEntity<List<Payment>> getAllByStudentId(@PathVariable String studentName){
        return ResponseEntity.ok(paymentService.getAllByStudentName(studentName));
    }

    @GetMapping("/last/{studentName}")
    public ResponseEntity<Payment> getLastPaymentByStudentName(@PathVariable String studentName){
        return ResponseEntity.ok(paymentService.getLastPaymentByStudentName(studentName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment (@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
