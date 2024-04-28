package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
