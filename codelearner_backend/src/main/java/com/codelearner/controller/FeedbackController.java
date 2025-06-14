package com.codelearner.controller;

import com.codelearner.model.Feedback;
import com.codelearner.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @PostMapping("/{studentId}/submit")
    public Feedback submitFeedback(@PathVariable String studentId, @RequestBody Feedback feedback) {
        feedback.setStudentId(studentId);
        return feedbackRepository.save(feedback);
    }

    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}
