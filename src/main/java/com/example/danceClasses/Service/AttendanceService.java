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

import java.util.List;

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
    public Attendance findStudentAttendanceByLessonId(Long lessonId, String studentName){
        Student student = studentRepository.findStudentByName(studentName);
        for (Attendance attendance:student.getAttendances()
             ) {
            if (attendance.getLesson().getId().equals(lessonId))
                return attendance;
        }
        return null;
    }

    @Transactional
    public Attendance addAttendance(Long lessonId, String studentName){
        Student student = studentRepository.findStudentByName(studentName);
        Lesson lesson = lessonRepository.findLessonById(lessonId);
        Attendance alreadyExists = findStudentAttendanceByLessonId(lessonId, studentName);
        if(alreadyExists != null)
            return alreadyExists;
        Attendance newAttendance = new Attendance(student,lesson,lesson.getDateAndTime());
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

    public List<Attendance> getAllByStudentId(Long studentId){
        return attendanceRepository.findAllByStudentId(studentId);
    }

    @Transactional
    public void deleteAttendance(Long id){
        if(!attendanceRepository.existsById(id))
            throw new ResourceNotFoundException("No attendance found with id "+ id);
        attendanceRepository.deleteById(id);
    }
}
