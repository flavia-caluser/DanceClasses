package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private LocalDateTime date;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    //TODO
    // de modificat relatia intre payment si lesson sa fie many t many
    //in tabelul de legatura vom pune bineinteles payment, lesson, dar si student , asa ca student nu va mai fi in payment
    //si atunci nu mai avem legatura intre paymet si course


    @OneToMany(mappedBy = "payment",  cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference("payment-lessonPayment")
    private List<LessonPayment> lessonPaymentList;

    public Payment(Long id, LocalDateTime date, PaymentMethod method, List<LessonPayment> lessonPaymentList) {
        this.id = id;
        this.date = date;
        this.method = method;
        this.lessonPaymentList = lessonPaymentList;
    }

    public Payment() {
    }

    public List<LessonPayment> getLessonPaymentList() {
        if(this.lessonPaymentList==null)
            this.lessonPaymentList = new ArrayList<>();
        return lessonPaymentList;
    }

    public void setLessonPaymentList(List<LessonPayment> lessonPaymentList) {
        this.lessonPaymentList = lessonPaymentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


//    @Override
//    public String toString() {
//        return "Payment{" +
//                "id=" + id +
//                ", date=" + date +
//                ", method=" + method +
//                ", lessonPaymentList=" + lessonPaymentList +
//                '}';
//    }
}
