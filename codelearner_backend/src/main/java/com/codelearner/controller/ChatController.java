package com.codelearner.controller;


import com.codelearner.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping
    public String askAI(@RequestBody String question) {
        return openAIService.askQuestion(question);
    }
}
