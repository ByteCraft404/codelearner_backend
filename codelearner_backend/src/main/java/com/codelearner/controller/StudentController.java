package com.codelearner.controller;

import com.codelearner.model.Student;
import com.codelearner.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*") 

public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student) {
        // Check if email already exists
        Student existing = studentRepository.findByEmail(student.getEmail());
        if (existing != null) {
            throw new RuntimeException("Email already registered");
        }
        return studentRepository.save(student);
    }

    @PostMapping("/login")
    public Student loginStudent(@RequestBody Student loginData) {
        Student found = studentRepository.findByEmail(loginData.getEmail());
        if (found != null && found.getPassword().equals(loginData.getPassword())) {
            return found;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
