//package com.example.danceClasses.Service;
//
//import com.example.danceClasses.Entities.Course;
//import com.example.danceClasses.Entities.Instructor;
//import com.example.danceClasses.Entities.Lesson;
//import com.example.danceClasses.Entities.Student;
//import com.example.danceClasses.Repositories.InstructorRepository;
//import com.example.danceClasses.Repositories.StudentRepository;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.MonthDay;
//import java.util.Comparator;
//import java.util.List;
//
//@Component
//public class CronJob {
//    private final MailService mailService;
//    private final StudentRepository studentRepository;
//    private final InstructorRepository instructorRepository;
//
//    public CronJob(MailService mailService, StudentRepository studentRepository,InstructorRepository instructorRepository) {
//        this.mailService = mailService;
//        this.studentRepository = studentRepository;
//        this.instructorRepository = instructorRepository;
//    }
//
//    //TODO:2.cronjob care ruleaza o data pe zi mail de anunt "urmeaza sa achiti".
//    // Exemplu: a achitat deja 3 lectii, dar in cursul la care vine mai urmeaza lectii
//    // asa ca iil anuntam ca va trebui sa achite incepand de la urmatoarea lectie
//    // cronjob care ruleaza o data pe zi si ia din db toti studentii care sunt in niste cursuri
//    // care rumeaza sa mai aiba lectii, dar care nu au o plata inregistrarta pentru urmatoarea
//    // lectie din curs
//    // 1.mail de hai la curs sa iti sarbatoresti ziua de nastere
//    // (sa se trimita mail-uri instructorilor: Azi e ziua de nastere a lui X, care e cursant
//    // la cursurile Y,Z,A/ SAU in fiecare luni la ora 12 sa se ruleze metoda si sa trimita mail
//    // instructorilor cu cei carora le va fi ziua in saptamana respectiva).
//
//    public Lesson findLessonByDate(LocalDate date, Course course) {
//        for (Lesson lesson : course.getLessons()) {
//            LocalDate parsedDate = lesson.getDateAndTime().toLocalDate();
//            if (parsedDate.equals(date))
//                return lesson;
//        }
//        return null;
//    }
//
//    public List<Lesson> getUnpaidTomorrowLessons(LocalDate tomorrow, Student student) {
//        return student.getCourses().stream()
//                .flatMap(course -> course.getLessons().stream())
//                .filter(lesson -> findLessonByDate(tomorrow, lesson.getCourse()) != null)
//                .filter(lesson -> lesson.getLessonPaymentList().isEmpty())
//                .toList();
//    }
//
//    @Scheduled(cron = "0 0 12 * * ?")
//    public void sendMailIfPaymentIsNecessary() {
//        List<Student> allStudents = studentRepository.findAll();
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        for (Student student : allStudents) {
//            if (getUnpaidTomorrowLessons(tomorrow, student).isEmpty())
//                return;
//            else if (getUnpaidTomorrowLessons(tomorrow, student).size() == 1) {
//                mailService.sendSimpleMessage(student.getEmailAddress(), "Plata lectii de dans",
//                        "Hola, " + student.getName() + ", maine vei avea o noua lectie de dans care " +
//                                "nu a fost inca achitata. Te asteptam cu drag!");
//            } else
//                mailService.sendSimpleMessage(student.getEmailAddress(), "Plata lectii de dans",
//                        "Hola, " + student.getName() + ", maine vei avea noi lectii de dans care " +
//                                "nu au fost inca achitate. Te asteptam cu drag!");
//        }
//    }
//
//    //FUNCTIONALITATE MAIL CU SARBATORITII PE SAPTAMANA(void)
//    //1.parcurg toti studentii
//    //2. le verific datele de nastere daca se incadreaza intre data din acea zi si de peste 8 zile
//    //3. daca se incadreazale adaug in string
//    //4. trimit mail instructorilor cu string-ul cu toti sarbatoritii din saptamana
//
//    public boolean checkIfBirthdayIsThisWeek(LocalDate studentDate, LocalDate sunday, LocalDate nextMonday) {
//        MonthDay studentBirthday = MonthDay.of(studentDate.getDayOfMonth(), studentDate.getDayOfMonth());
//        MonthDay yesterday = MonthDay.of(sunday.getDayOfMonth(), sunday.getDayOfMonth());
//        MonthDay nextWeek = MonthDay.of(nextMonday.getDayOfMonth(), nextMonday.getDayOfMonth());
//        if (studentBirthday.isAfter(yesterday) && studentBirthday.isBefore(nextWeek))
//            return true;
//        return false;
//    }
//
//    @Scheduled(cron = "0 0 12 * * MON")
//    public void sendMailWithWeeklyBirthdays() {
//        List<Student> allStudents = studentRepository.findAll();
//        List<Instructor> allInstructors = instructorRepository.findAll();
//        LocalDate sunday = LocalDate.now().minusDays(1);
//        LocalDate nextMonday = sunday.plusDays(8);
//        String allBirthdaysOfTheWeek = "";
//        for (Student student : allStudents) {
//            if (checkIfBirthdayIsThisWeek(student.getBirthDate(), sunday, nextMonday))
//                allBirthdaysOfTheWeek = allBirthdaysOfTheWeek + "" + student.getName() + " in data de " + student.getBirthDate();
//        }
//        if (!allBirthdaysOfTheWeek.isEmpty())
//            for (Instructor instructor: allInstructors){
//                mailService.sendSimpleMessage(instructor.getEmailAddress(), "Sarbatoritii din aceasta saptamana", "Sarbatoritii sunt "+ allBirthdaysOfTheWeek);
//            }
//    }
//
//}
