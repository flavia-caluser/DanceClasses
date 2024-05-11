package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentById(Long id);
    Student findStudentByName(String name);
}
