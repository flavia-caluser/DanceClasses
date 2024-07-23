package com.example.danceClasses.Mapper;

import com.example.danceClasses.DTOS.CourseResponseDTO;
import com.example.danceClasses.Entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public  static CourseResponseDTO fromCourseToResponseDTO(Course course){
        CourseResponseDTO response = new CourseResponseDTO();
        response.setName(course.getName());
        response.setInstructors(course.getInstructors());
        response.setEndDate(course.getEndDate());
        response.setStartDate(course.getStartDate());
        return response;
    }

}
