package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByStudentId(Long studentId);
}
