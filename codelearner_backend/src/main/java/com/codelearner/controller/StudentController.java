package com.codelearner.controller;

import com.codelearner.model.Student;
import com.codelearner.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        Student existing = studentRepository.findByEmail(student.getEmail());
        if (existing != null) {
            // Return 409 Conflict with a message
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }
        Student saved = studentRepository.save(student);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody Student loginData) {
        Student found = studentRepository.findByEmail(loginData.getEmail());
        if (found != null && found.getPassword().equals(loginData.getPassword())) {
            return ResponseEntity.ok(found);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
