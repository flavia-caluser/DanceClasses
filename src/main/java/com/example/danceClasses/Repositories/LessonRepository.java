package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
