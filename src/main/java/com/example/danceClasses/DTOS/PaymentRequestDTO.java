package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.PaymentMethod;

import java.time.LocalDate;

public class PaymentRequestDTO {

    private LocalDate date;
    private String studentName;
    private PaymentMethod paymentMethod;
    private String courseName;

    public PaymentRequestDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
