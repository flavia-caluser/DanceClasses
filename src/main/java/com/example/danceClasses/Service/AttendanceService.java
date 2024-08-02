package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.AttendanceRequestDTO;
import com.example.danceClasses.Entities.Attendance;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.AttendanceRepository;
import com.example.danceClasses.Repositories.LessonRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private StudentRepository studentRepository;
    private LessonRepository lessonRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional
    public Attendance addAttendance(AttendanceRequestDTO attendanceRequestDTO){
        Student student = studentRepository.findStudentByName(attendanceRequestDTO.getStudentName());
        Lesson lesson = lessonRepository.findByName(attendanceRequestDTO.getLessonName());
        Attendance newAttendance = new Attendance();
        newAttendance.setStudent(student);
        newAttendance.setLesson(lesson);
        student.getAttendances().add(newAttendance);
        studentRepository.save(student);
        lessonRepository.save(lesson);
        return attendanceRepository.save(newAttendance);
    }

    public List<Attendance> getAllByStudentId(Long studentId){
        return attendanceRepository.findAllByStudentId(studentId);
    }
}
