package com.example.danceClasses.Mapper;

import com.example.danceClasses.DTOS.CourseResponseDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Instructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public  static CourseResponseDTO fromCourseToResponseDTO(Course course){
        CourseResponseDTO response = new CourseResponseDTO();
        response.setName(course.getName());
        response.setInstructorsNames(course.getInstructors().stream()
                .map(Instructor::getName)
                .collect(Collectors.toSet()));
        response.setEndDate(course.getEndDate());
        response.setStartDate(course.getStartDate());
        response.setLessonPrice(course.getLessonPrice());
        return response;
    }

}
