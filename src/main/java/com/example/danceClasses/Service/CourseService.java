package com.example.danceClasses.Service;

import com.example.danceClasses.DTOS.CourseRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course addCourse(CourseRequestDTO courseRequestDTO){
        Course newCourse = new Course();
        newCourse.setName(courseRequestDTO.getName());
        newCourse.setInstructors(new HashSet<>());
        newCourse.setStudents(new HashSet<>());
        newCourse.setLessons(new HashSet<>());
        newCourse.setReviews(new ArrayList<>());
        return courseRepository.save(newCourse);
    }
}
