package com.practice.student.service.service;

import com.practice.student.service.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);
}
