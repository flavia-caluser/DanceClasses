package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.CourseRequestDTO;
import com.example.danceClasses.DTOS.CourseResponseDTO;
import com.example.danceClasses.DTOS.LessonRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Repositories.CourseRepository;
import com.example.danceClasses.Repositories.InstructorRepository;
import com.example.danceClasses.Repositories.LessonRepository;
import com.example.danceClasses.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private InstructorRepository instructorRepository;
    private LessonRepository lessonRepository;
    private StudentRepository studentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course addCourse(CourseRequestDTO courseRequestDTO){
        Course newCourse = new Course();
        newCourse.setName(courseRequestDTO.getName());
        Set<Instructor> instructors = new HashSet<>();
        for(String name: courseRequestDTO.getInstructorsNames()){
            instructors.add(instructorRepository.findInstructorByName(name));
        }
        newCourse.setInstructors(instructors);
        return courseRepository.save(newCourse);
    }

    @Transactional
    public Lesson addLesson(LessonRequestDTO lessonRequestDTO){
        Course course = courseRepository.findCourseByName(lessonRequestDTO.getCourseName());
        Lesson newLesson = new Lesson();
        newLesson.setName(lessonRequestDTO.getName());
        newLesson.setCourse(course);
        course.getLessons().add(newLesson);
        return lessonRepository.save(newLesson);
    }

    //TO DO: mapper de la course la courseResponseDTO
//    public List<Course> getAllCourses() {
//        List<Course> coursesList = courseRepository.findAll();
//        coursesList.stream()
//                .map(course->)
//    }

}
