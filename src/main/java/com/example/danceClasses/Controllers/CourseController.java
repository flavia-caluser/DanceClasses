package com.example.danceClasses.Controllers;
import java.util.List;
import java.util.Map;

import com.example.danceClasses.DTOS.*;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Entities.Review;
import com.example.danceClasses.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

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

    @GetMapping("/getRevenues/{courseId}")
    public ResponseEntity<Double> getRevenuesForCourseBetweenDates(@PathVariable Long courseId, @RequestBody DatesRequestDTO datesRequestDTO){
        return ResponseEntity.ok(courseService.getRevenuesForCourseBetweenDates(courseId,datesRequestDTO));
    }

    @GetMapping("/getRevenues/forAll")
    public ResponseEntity<Map<String,Double>> getAllRevenuesBetweenDates(@RequestBody DatesRequestDTO datesRequestDTO){
        return ResponseEntity.ok(courseService.getAllRevenuesBetweenDates(datesRequestDTO));
    }

    @PutMapping("/change/name/{courseId}/{newName}")
    public ResponseEntity<CourseResponseDTO> changeCourseName(@PathVariable Long courseId, @PathVariable String newName){
        return ResponseEntity.ok(courseService.changeCourseName(courseId,newName));
    }
    @PutMapping("/change/dates/{courseId}")
    public ResponseEntity<CourseResponseDTO> changeCourseDates(@PathVariable Long courseId, @RequestBody DatesRequestDTO newDates){
        return ResponseEntity.ok(courseService.changeCourseDates(courseId, newDates));
    }

    @PutMapping("/change/lessonPrice/{courseId}")
    public ResponseEntity<CourseResponseDTO> changeLessonPriceForCourse(@PathVariable Long courseId,@RequestBody Double newPrice){
        return ResponseEntity.ok(courseService.changeLessonPriceForCourse(courseId, newPrice));
    }

    @PutMapping("/change/lessonPrice/all")
    public ResponseEntity<String> changeLessonPriceForAll( @RequestBody Double newPrice){
        courseService.changeLessonPriceForAll(newPrice);
        return ResponseEntity.ok("Update successful");
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
