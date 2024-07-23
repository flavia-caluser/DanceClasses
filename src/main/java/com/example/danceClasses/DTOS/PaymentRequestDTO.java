package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.PaymentMethod;

import java.time.LocalDate;
import java.util.List;

public class PaymentRequestDTO {

    private LocalDate date;

    private PaymentMethod paymentMethod;

    private List<LessonPaymentsRequestDTO> lessonPaymentsRequestDTOList;

    public PaymentRequestDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<LessonPaymentsRequestDTO> getLessonPaymentsRequestDTOList() {
        return lessonPaymentsRequestDTOList;
    }

    public void setLessonPaymentsRequestDTOList(List<LessonPaymentsRequestDTO> lessonPaymentsRequestDTOList) {
        this.lessonPaymentsRequestDTOList = lessonPaymentsRequestDTOList;
    }
}
