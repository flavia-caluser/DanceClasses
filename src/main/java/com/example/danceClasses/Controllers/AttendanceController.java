package com.example.danceClasses.Controllers;

import com.example.danceClasses.DTOS.AttendanceRequestDTO;
import com.example.danceClasses.Entities.Attendance;
import com.example.danceClasses.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
    @PostMapping("/add")
    public ResponseEntity<Attendance> addAttendance(@RequestBody AttendanceRequestDTO attendanceRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.addAttendance(attendanceRequestDTO));
    }

    @GetMapping("/all/{studentId}")
    public ResponseEntity<List<Attendance>> getAllByStudentId(@PathVariable Long studentId){
        return ResponseEntity.ok(attendanceService.getAllByStudentId(studentId));
    }
}
