package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Instructor findInstructorByName(String name);
}
