package com.example.danceClasses;

import com.example.danceClasses.DTOS.InstructorRequestDTO;
import com.example.danceClasses.Entities.Instructor;
import com.example.danceClasses.Service.InstructorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InstructorServiceTest {
    @Autowired
    private InstructorService instructorService;

    @Test
    public void contextLoads() {
        // Verifică dacă instructorService este injectat corect
        assertNotNull(instructorService);
    }

    @Test
    public void testAddInstructor() {
        InstructorRequestDTO requestDTO = new InstructorRequestDTO();
        requestDTO.setName("John Doe");
        Instructor instructor = instructorService.addInstructor(requestDTO);
        assertNotNull(instructor);
        assertEquals("John Doe", instructor.getName());
    }

    @Test
    public void testFindInstructorByName() {
        String instructorName = "John Doe";
        Instructor instructor = instructorService.findInstructorByName(instructorName);
        assertEquals(instructorName, instructor.getName());
    }


}
