package com.example.danceClasses.Controllers;

import com.example.danceClasses.Entities.Attendance;
import com.example.danceClasses.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/add/{lessonId}/{studentName}")
    public ResponseEntity<Attendance> addAttendance(@PathVariable Long lessonId, @PathVariable String studentName){
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.addAttendance(lessonId, studentName));
    }

    @GetMapping("/all/{studentName}")
    public ResponseEntity<Map<String,List<Attendance>>> getAllByStudentName(@PathVariable String studentName){
        return ResponseEntity.ok(attendanceService.getAllByStudentName(studentName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id){
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
