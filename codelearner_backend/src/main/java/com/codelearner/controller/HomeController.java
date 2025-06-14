package com.codelearner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "🎓 Welcome to CodeLearner API!\nYour backend is running successfully.";
    }
}
