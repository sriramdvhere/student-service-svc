package com.practice.student.service.controller;

import com.practice.student.service.model.Student;
import com.practice.student.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * return all students
     *
     * @return return all students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * creating new student, if already student details received, will return already existing student details
     *
     * @param student student data in json
     * @return create new student if not available before
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    /**
     * return single student details
     *
     * @param id id of student
     * @return return single student details
     */
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null)
            return ResponseEntity.ok(student);
        return ResponseEntity.notFound().build();
    }
}
