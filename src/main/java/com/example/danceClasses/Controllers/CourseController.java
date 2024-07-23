package com.example.danceClasses.Controllers;
import java.util.List;
import com.example.danceClasses.DTOS.CourseRequestDTO;
import com.example.danceClasses.DTOS.CourseResponseDTO;
import com.example.danceClasses.DTOS.LessonRequestDTO;
import com.example.danceClasses.Entities.Course;
import com.example.danceClasses.Entities.Lesson;
import com.example.danceClasses.Service.CourseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    //TODO functionalitati extra
    //mail de hai la curs sa iti sarbatoresti ziua de nastere - cronjob care ruleaza o data pe zi
    //mail de anunt "urmeaza sa achiti". Exemplu: a achitat deja 3 lectii, dar in cursul la care vine mai urmeaza lectii asa ca iil anuntam ca va trebui sa achite incepand de la urmatoarea lectie
        //cronjob care ruleaza o data pe zi si ia din db toti studentii care sunt in niste cursuri care rumeaza sa mai aiba lectii, dar care nu au o plata inregistrarta pentru urmatoarea lectie din curs
    //statistica de cat am incasat pe un anumit curs
    //statistica cati cursanti am in cursuri de incepatori/inter.avansati

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse (@RequestBody CourseRequestDTO courseRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseRequestDTO));
    }

    @PostMapping("/lesson")
    public ResponseEntity<Lesson> addLesson (@RequestBody LessonRequestDTO lessonRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addLesson((lessonRequestDTO)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
