package com.practice.student.service;

import com.practice.student.service.controller.StudentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.practice.student.service.model.Student;
import com.practice.student.service.service.StudentService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testGetAllStudents() throws Exception {
        Student student1 = new Student(1L, "John Doe", 20, "IND001", "john@example.com");
        Student student2 = new Student(2L, "Jane Doe", 22, "IND002", "jane@example.com");

        given(studentService.getAllStudents()).willReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student newStudent = new Student(1L, "Alice", 23, "IND001", "alice@example.com");
        Student savedStudent = new Student(1L, "Alice", 23, "IND001", "alice@example.com");

        given(studentService.addStudent(newStudent)).willReturn(savedStudent);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"govtId\":\"IND001\",\"name\":\"Alice\",\"age\":23,\"email\":\"alice@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    public void testGetStudentById() throws Exception {
        Student student = new Student(1L, "Bob", 24, "IND001", "bob@example.com");

        given(studentService.getStudentById(1L)).willReturn(student);

        mockMvc.perform(get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
    }
}
