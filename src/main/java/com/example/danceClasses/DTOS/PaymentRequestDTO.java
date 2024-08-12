package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentRequestDTO {

    private LocalDateTime date;

    private PaymentMethod paymentMethod;

    @JsonProperty("lessonPaymentRequestDTO")
    private List<LessonPaymentRequestDTO> lessonPaymentRequestDTOList = new ArrayList<>();

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

    public List<LessonPaymentRequestDTO> getLessonPaymentRequestDTOList() {
        if(this.lessonPaymentRequestDTOList==null)
            this.lessonPaymentRequestDTOList =  new ArrayList<>();
        return lessonPaymentRequestDTOList;
    }

    public void setLessonPaymentRequestDTOList(List<LessonPaymentRequestDTO> lessonPaymentRequestDTOList) {
        this.lessonPaymentRequestDTOList = lessonPaymentRequestDTOList;
    }
}
