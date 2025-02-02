package com.practice.student.service.repository;

import com.practice.student.service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    Student findByGovtId(String govtId);
}
