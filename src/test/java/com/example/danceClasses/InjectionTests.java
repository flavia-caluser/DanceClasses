package com.example.danceClasses;

import com.example.danceClasses.Repositories.InstructorRepository;
import com.example.danceClasses.Service.InstructorService;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InjectionTests {
    @Autowired
    private InstructorService instructorService;

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    public void RepositoryInjection(){
        assertNotNull(instructorService);
        assertThat(instructorService).extracting("instructorRepository").isNull();
    }
}
