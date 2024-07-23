package com.example.danceClasses.Mapper;

import com.example.danceClasses.DTOS.LessonPaymentsRequestDTO;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.LessonPayment;
import com.example.danceClasses.Entities.Payment;
import com.example.danceClasses.Repositories.LessonRepository;
import org.springframework.stereotype.Component;

@Component
public class LessonPaymentMapper {
    private LessonRepository lessonRepository;

    public LessonPayment fromDTOToLessonPayment(LessonPaymentsRequestDTO lessonPaymentsRequestDTO) {
        LessonPayment result = new LessonPayment();
        result.setPayment(new Payment());
        Lesson lesson = lessonRepository.findByName(lessonPaymentsRequestDTO.getLessonName());
        result.setLesson(lesson);
        return result;
    }
}
