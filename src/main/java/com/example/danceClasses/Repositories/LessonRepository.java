package com.example.danceClasses.Repositories;

import com.example.danceClasses.Entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Lesson findByName(String name);
    Lesson findLessonById (Long id);
}
