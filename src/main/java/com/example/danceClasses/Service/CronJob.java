package com.example.danceClasses.Service;

import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CronJob {
    private final MailService mailService;
    private final StudentRepository studentRepository;

    public CronJob(MailService mailService, StudentRepository studentRepository) {
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

    public List<Lesson> getUnpaidTomorrowLessons(LocalDate tomorrow, Student student){
       return student.getCourses().stream()
                .flatMap(course -> course.getLessons().stream())
                .filter(lesson -> findLessonByDate(tomorrow, lesson.getCourse())!=null)
                .filter(lesson-> lesson.getLessonPaymentList().isEmpty())
                .toList();
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void sendMailIfPaymentIsNecessary(){
        List<Student> allStudents = studentRepository.findAll();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        for (Student student:allStudents){
           if(getUnpaidTomorrowLessons(tomorrow, student).isEmpty())
               return;
           else if (getUnpaidTomorrowLessons(tomorrow,student).size()==1){
               mailService.sendSimpleMessage(student.getEmailAddress(), "Plata lectii de dans",
                       "Hola, "+ student.getName()+ ", maine vei avea o noua lectie de dans care " +
                               "nu a fost inca achitata. Te asteptam cu drag!");
           }else
               mailService.sendSimpleMessage(student.getEmailAddress(), "Plata lectii de dans",
                       "Hola, "+ student.getName()+ ", maine vei avea noi lectii de dans care " +
                               "nu au fost inca achitate. Te asteptam cu drag!");
        }
    }

}
