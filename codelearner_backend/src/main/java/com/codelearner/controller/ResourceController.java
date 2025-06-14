package com.codelearner.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private static final Map<String, List<String>> RESOURCE_MAP = Map.of(
        "Data Structures", List.of(
            "https://www.youtube.com/watch?v=RBSGKlAvoiM",
            "https://www.geeksforgeeks.org/data-structures/"
        ),
        "Java Basics", List.of(
            "https://www.w3schools.com/java/",
            "https://www.youtube.com/watch?v=eIrMbAQSU34"
        )
    );

    @GetMapping("/search")
    public List<String> getResources(@RequestParam String topic) {
        return RESOURCE_MAP.getOrDefault(topic, List.of("No resources found for this topic."));
    }
}
