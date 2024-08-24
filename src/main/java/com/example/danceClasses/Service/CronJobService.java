package com.example.danceClasses.Service;

import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CronJobService {
    private final MailService mailService;
    private final StudentRepository studentRepository;

    public CronJobService(MailService mailService, StudentRepository studentRepository) {
        this.mailService = mailService;
        this.studentRepository = studentRepository;
    }

    //TODO:2.cronjob care ruleaza o data pe zi mail de anunt "urmeaza sa achiti".
    // Exemplu: a achitat deja 3 lectii, dar in cursul la care vine mai urmeaza lectii
    // asa ca iil anuntam ca va trebui sa achite incepand de la urmatoarea lectie
    // cronjob care ruleaza o data pe zi si ia din db toti studentii care sunt in niste cursuri
    // care rumeaza sa mai aiba lectii, dar care nu au o plata inregistrarta pentru urmatoarea
    // lectie din curs

    public Lesson findLessonByDate(LocalDate date, Course course){
        for(Lesson lesson:course.getLessons()){
            LocalDate parsedDate = lesson.getDateAndTime().toLocalDate();
            if(parsedDate.equals(date))
                return lesson;
        }
        return null;
    }
    //verific daca la vreun curs al studentului va fi maine o lectie
    //si verific daca nu a platit-o, atunci mail, altfel mai departe

//    public void sendMailIfPaymentNecessary(){
//        List<Student> allStudents = studentRepository.findAll();
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        for (Student student:allStudents){
//            for (Course course:student.getCourses()){
//                Lesson lesson = findLessonByDate(tomorrow, course);
//                if(!student.getLessonPayment().getLesson().equals(lesson))
//                    mailService.sendSimpleMessage(student.getEmailAddress(), );
//            }
//        }
//    }

}
