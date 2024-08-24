package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.CourseRequestDTO;
import com.example.danceClasses.DTOS.CourseResponseDTO;
import com.example.danceClasses.DTOS.LessonRequestDTO;
import com.example.danceClasses.DTOS.ReviewRequestDTO;
import com.example.danceClasses.Entities.*;
import com.example.danceClasses.Exceptions.ResourceNotFoundException;
import com.example.danceClasses.Mapper.CourseMapper;
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

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final LessonRepository lessonRepository;
    private final ReviewRepository reviewRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository,
                         LessonRepository lessonRepository, ReviewRepository reviewRepository,StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.lessonRepository = lessonRepository;
        this.reviewRepository = reviewRepository;
        this.studentRepository = studentRepository;
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

    @Transactional
    public Review addReview(Long courseId,Long studentId, ReviewRequestDTO reviewRequestDTO){
        Review newReview = new Review();
        newReview.setComment(reviewRequestDTO.getComment());
        Course course = courseRepository.findCourseById(courseId);
        newReview.setCourse(course);
        Student student = studentRepository.findStudentById(studentId);
        newReview.setStudent(student);
        courseRepository.save(course);
        studentRepository.save(student);
        return reviewRepository.save(newReview);
    }

    @Transactional
    public Review addAnonymousReview(Long courseId,ReviewRequestDTO reviewRequestDTO){
        Review newReview = new Review();
        newReview.setComment(reviewRequestDTO.getComment());
        Course course = courseRepository.findCourseById(courseId);
        newReview.setCourse(course);
        courseRepository.save(course);
        return reviewRepository.save(newReview);
    }

    public List<CourseResponseDTO> getAllCourses() {
        List<Course> coursesList = courseRepository.findAll();
       return coursesList.stream()
                .map(CourseMapper::fromCourseToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Review> getAllReviewsForCourse(Long courseId){
        List<Review> courseReviews = reviewRepository.findAllByCourseId(courseId);
        if(courseReviews.isEmpty())
            throw new ResourceNotFoundException("The course with id "+ courseId +" has no reviews");
        return courseReviews;
    }

    public CourseResponseDTO changeCourseName(Long courseId, String newName){
        Course course = courseRepository.findCourseById(courseId);
        course.setName(newName);
        courseRepository.save(course);
        return fromCourseToResponseDTO(course);
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
