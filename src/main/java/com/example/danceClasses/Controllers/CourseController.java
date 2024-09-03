package com.example.danceClasses.Controllers;
import java.util.List;
import com.example.danceClasses.DTOS.CourseRequestDTO;
import com.example.danceClasses.DTOS.CourseResponseDTO;
import com.example.danceClasses.DTOS.LessonRequestDTO;
import com.example.danceClasses.DTOS.ReviewRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Review;
import com.example.danceClasses.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    //TODO functionalitati extra: 1.mail de hai la curs sa iti sarbatoresti ziua de nastere
    // (sa se trimita mail-uri instructorilor: Azi e ziua de nastere a lui X, care e cursant
    // la cursurile Y,Z,A/ SAU in fiecare luni la ora 12 sa se ruleze metoda si sa trimita mail
    // instructorilor cu cei carora le va fi ziua in saptamana respectiva).
    // statistica de cat am incasat pe un anumit curs
    // statistica cati cursanti am in cursuri de incepatori/inter.avansati

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse (@RequestBody CourseRequestDTO courseRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseRequestDTO));
    }

    @PostMapping("/addLesson")
    public ResponseEntity<Lesson> addLesson (@RequestBody LessonRequestDTO lessonRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addLesson((lessonRequestDTO)));
    }

    @PostMapping("/addReview/{courseId}/{studentId}")
    public ResponseEntity<Review> addReview(@PathVariable Long courseId, @PathVariable Long studentId, @RequestBody ReviewRequestDTO reviewRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addReview(courseId, studentId, reviewRequestDTO));
    }

    @PostMapping("/addReview/{courseId}")
    public ResponseEntity<Review> addAnonymousReview(@PathVariable Long courseId,@RequestBody ReviewRequestDTO reviewRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addAnonymousReview(courseId, reviewRequestDTO));
    }

    @GetMapping("/allCourseReviews/{courseId}")
    public ResponseEntity<List<Review>> getAllReviewsForCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.getAllReviewsForCourse(courseId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping("/changeName/{courseId}/{newName}")
    public ResponseEntity<CourseResponseDTO> changeCourseName(@PathVariable Long courseId, @PathVariable String newName){
        return ResponseEntity.ok(courseService.changeCourseName(courseId,newName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteLesson/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id){
        courseService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        courseService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
