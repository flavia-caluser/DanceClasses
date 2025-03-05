package com.example.danceClasses.DTOS;

import com.example.danceClasses.Entities.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentResponseDTO {

    private LocalDateTime date;

    private PaymentMethod method;
    private List<String> lessonsNames;

    public PaymentResponseDTO(LocalDateTime date, PaymentMethod method, List<String> lessonsNames) {
        this.date = date;
        this.method = method;
        this.lessonsNames = lessonsNames;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public List<String> getLessonsNames() {
        return lessonsNames;
    }

    public void setLessonsNames(List<String> lessonsNames) {
        this.lessonsNames = lessonsNames;
    }
}
