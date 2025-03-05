package com.example.danceClasses.Service;

import com.example.danceClasses.Entities.Attendance;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
import com.example.danceClasses.Repositories.AttendanceRepository;
import com.example.danceClasses.Repositories.CourseRepository;
import com.example.danceClasses.Repositories.LessonRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository, LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public Attendance findStudentAttendanceByLessonId(Long lessonId, String studentName) {
        Student student = studentRepository.findStudentByName(studentName);
        for (Attendance attendance : student.getAttendances()
        ) {
            if (attendance.getLesson().getId().equals(lessonId))
                return attendance;
        }
        return null;
    }

    @Transactional
    public Attendance addAttendance(Long lessonId, String studentName) {
        Student student = studentRepository.findStudentByName(studentName);
        Lesson lesson = lessonRepository.findLessonById(lessonId);
        Attendance alreadyExists = findStudentAttendanceByLessonId(lessonId, studentName);
        if (alreadyExists != null)
            return alreadyExists;
        Attendance newAttendance = new Attendance(student, lesson, lesson.getDateAndTime());
        student.getAttendances().add(newAttendance);
        Course newCourse = lesson.getCourse();
        if (!student.getCourses().contains(newCourse)) {
            student.getCourses().add(newCourse);
            newCourse.getStudents().add(student);
        }
        studentRepository.save(student);
        courseRepository.save(newCourse);
        return attendanceRepository.save(newAttendance);
    }

    //in mapa trebuie sa fie toate prezentele puse pe cursuri(mapa)
    //am o lista cu toate prezentele studentului
    //parcurg lista si le adaug pe rand pe fiecare in mapa
    //prima data verific daca exista cursul respectiv in mapa
    //daca nu il adaug
    //daca da adaug direct prezenta la curs
    //returnez mapa
    public Map<String, List<Attendance>> getAllByStudentName(String studentName) {
        Map<String, List<Attendance>> allAttendancesByCourse = new HashMap<>();
        Long studentId = studentRepository.findStudentByName(studentName).getId();
        List<Attendance> allAttendances = attendanceRepository.findAllByStudentId(studentId);
        for (Attendance attendance : allAttendances) {
            String courseName = attendance.getLesson().getCourse().getName();
            if (!allAttendancesByCourse.containsKey(courseName)) {
                List<Attendance> list = new ArrayList<>();
                list.add(attendance);
                allAttendancesByCourse.put(courseName, list);
            } else
                allAttendancesByCourse.get(courseName).add(attendance);
        }
        return allAttendancesByCourse;
    }

    @Transactional
    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id))
            throw new ResourceNotFoundException("No attendance found with id " + id);
        attendanceRepository.deleteById(id);
    }
}
