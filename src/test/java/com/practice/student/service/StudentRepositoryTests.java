package com.practice.student.service;


import com.practice.student.service.model.Student;
import com.practice.student.service.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class StudentRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setAge(25);
        student.setEmail("john.doe@example.com");
        student.setGovtId("IND23123");
        student = entityManager.persistAndFlush(student); // Persist and immediately flush to database

        Student found = studentRepository.findByGovtId(student.getGovtId());
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(student.getName());
        System.out.println(found.toString());
    }
}
