package com.example.danceClasses.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private LocalDate date;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    //TODO
    // de modificat relatia intre payment si lesson sa fie many t many
    //in tabelul de legatura vom pune bineinteles payment, lesson, dar si student , asa ca student nu va mai fi in payment
    //si atunci nu mai avem legatura intre paymet si course


    @OneToMany(mappedBy = "payment",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("payment-lessonPayment")
    private List<LessonPayment> lessonPaymentList;

    public Payment() {
    }

    public List<LessonPayment> getLessonPaymentList() {
        return lessonPaymentList;
    }

    public void setLessonPaymentList(List<LessonPayment> lessonPaymentList) {
        lessonPaymentList = lessonPaymentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }



    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", method=" + method +
                '}';
    }
}
