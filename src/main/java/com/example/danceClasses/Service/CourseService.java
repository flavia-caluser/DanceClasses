package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.CourseRequestDTO;
import com.example.danceClasses.DTOS.CourseResponseDTO;
import com.example.danceClasses.DTOS.LessonRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Student;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
import com.example.danceClasses.Repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.danceClasses.Mapper.CourseMapper.fromCourseToResponseDTO;

@Service
public class CourseService {
//    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final LessonRepository lessonRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository, LessonRepository lessonRepository, ReviewRepository reviewRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.lessonRepository = lessonRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Course addCourse(CourseRequestDTO courseRequestDTO){
        Course newCourse = new Course();
        newCourse.setName(courseRequestDTO.getName());
        Set<Instructor> instructors = new HashSet<>();
        for(String name: courseRequestDTO.getInstructorsNames()){
            instructors.add(instructorRepository.findInstructorByName(name));
//            logger.info("Instructor {} has been added successfully.",name);
        }
        newCourse.setInstructors(instructors);
        newCourse.setStartDate(courseRequestDTO.getStartDate());
        newCourse.setEndDate(courseRequestDTO.getEndDate());
        return courseRepository.save(newCourse);
    }

    @Transactional
    public Lesson addLesson(LessonRequestDTO lessonRequestDTO){
        Course course = courseRepository.findCourseByName(lessonRequestDTO.getCourseName());
        Lesson newLesson = new Lesson();
        newLesson.setName(lessonRequestDTO.getName());
        newLesson.setCourse(course);
        newLesson.setDateAndTime(lessonRequestDTO.getDateAndTime());
        course.getLessons().add(newLesson);
        return lessonRepository.save(newLesson);
    }

    //TODO: mapper de la course la courseResponseDTO
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> coursesList = courseRepository.findAll();
       return coursesList.stream()
                .map(course->fromCourseToResponseDTO(course))
                .collect(Collectors.toList());
    }

    public void deleteCourse (Long id){
        if(!courseRepository.existsById(id))
            throw new ResourceNotFoundException("Course not found with id "+ id);
        courseRepository.deleteById(id);
    }

    public void deleteLesson (Long id){
        if(!lessonRepository.existsById(id))
            throw new ResourceNotFoundException("Lesson not found with id "+ id);
        lessonRepository.deleteById(id);
    }

    public void deleteReview(Long id){
        if (!reviewRepository.existsById(id))
            throw new ResourceNotFoundException("Review not found with id "+ id);
        reviewRepository.deleteById(id);
    }
}
