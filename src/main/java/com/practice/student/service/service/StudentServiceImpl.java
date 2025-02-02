package com.practice.student.service.service;

import com.practice.student.service.exception.NoSuchStudentException;
import com.practice.student.service.model.Student;
import com.practice.student.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;


    @Override
    public Student addStudent(Student student) {
        Student existingStudent = studentRepository.findByGovtId(student.getGovtId());
        if (existingStudent != null)
            return existingStudent;
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElse(null);
    }
}
