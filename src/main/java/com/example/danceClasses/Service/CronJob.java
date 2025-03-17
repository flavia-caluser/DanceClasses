package com.example.danceClasses.Service;

import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.InstructorRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.Comparator;
import java.util.List;

@Component
public class CronJob {
    private final MailService mailService;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    public CronJob(MailService mailService, StudentRepository studentRepository,InstructorRepository instructorRepository) {
        this.mailService = mailService;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
    }


    public Lesson findLessonByDate(LocalDate date, Course course) {
        for (Lesson lesson : course.getLessons()) {
            LocalDate parsedDate = lesson.getDateAndTime().toLocalDate();
            if (parsedDate.equals(date))
                return lesson;
        }
        return null;
    }

    public List<Lesson> getUnpaidTomorrowLessons(LocalDate tomorrow, Student student) {
        return student.getCourses().stream()
                .flatMap(course -> course.getLessons().stream())
                .filter(lesson -> findLessonByDate(tomorrow, lesson.getCourse()) != null)
                .filter(lesson -> lesson.getLessonPaymentList().isEmpty())
                .toList();
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void sendMailIfPaymentIsNecessary() {
        List<Student> allStudents = studentRepository.findAll();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        for (Student student : allStudents) {
            if (getUnpaidTomorrowLessons(tomorrow, student).isEmpty())
                return;
            else if (getUnpaidTomorrowLessons(tomorrow, student).size() == 1) {
                mailService.sendSimpleMessage(student.getEmailAddress(), "Plata lectii de dans",
                        "Hola, " + student.getName() + ", maine vei avea o noua lectie de dans care " +
                                "nu a fost inca achitata. Te asteptam cu drag!");
            } else
                mailService.sendSimpleMessage(student.getEmailAddress(), "Plata lectii de dans",
                        "Hola, " + student.getName() + ", maine vei avea noi lectii de dans care " +
                                "nu au fost inca achitate. Te asteptam cu drag!");
        }
    }


    public boolean checkIfBirthdayIsThisWeek(LocalDate studentDate, LocalDate sunday, LocalDate nextMonday) {
        LocalDate birthdayThisYear = LocalDate.of(sunday.getYear(), studentDate.getMonth(), studentDate.getDayOfMonth());

        return !birthdayThisYear.isBefore(sunday) && birthdayThisYear.isBefore(nextMonday);
    }


    @Scheduled(cron = "0 0 12 * * MON")
    public void sendMailWithWeeklyBirthdays() {
        List<Student> allStudents = studentRepository.findAll();
        List<Instructor> allInstructors = instructorRepository.findAll();
        LocalDate sunday = LocalDate.now().minusDays(1);
        LocalDate nextMonday = sunday.plusDays(8);
        String allBirthdaysOfTheWeek = "";
        for (Student student : allStudents) {
            if (checkIfBirthdayIsThisWeek(student.getBirthDate(), sunday, nextMonday))
                allBirthdaysOfTheWeek = allBirthdaysOfTheWeek + "" + student.getName() + " in data de " + student.getBirthDate();
        }
        if (!allBirthdaysOfTheWeek.isEmpty())
            for (Instructor instructor: allInstructors){
                mailService.sendSimpleMessage(instructor.getEmailAddress(), "Sarbatoritii din aceasta saptamana", "Sarbatoritii sunt "+ allBirthdaysOfTheWeek);
            }
    }

}
