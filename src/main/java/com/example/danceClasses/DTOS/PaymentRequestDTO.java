package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentRequestDTO {

    private LocalDateTime date;

    private PaymentMethod paymentMethod;

    private List<String> lessonNameList;

    public PaymentRequestDTO() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<String> getLessonNameList() {
        return lessonNameList;
    }

    public void setLessonNameList(List<String> lessonNameList) {
        this.lessonNameList = lessonNameList;
    }
}
