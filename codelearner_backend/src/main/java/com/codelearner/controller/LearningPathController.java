package com.codelearner.controller;

import com.codelearner.model.LearningPath;
import com.codelearner.repository.LearningPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/learning-path")
@CrossOrigin("*")
public class LearningPathController {

    @Autowired
    private LearningPathRepository learningPathRepository;

    
    @PostMapping("/generate/{studentId}")
    public LearningPath generatePath(@PathVariable String studentId, @RequestBody Map<String, Object> payload) {
        String career = (String) payload.get("careerGoal");
        int weeks = (int) payload.get("studyWeeks");

       
        List<String> topics = Arrays.asList("Intro to Java", "Object Oriented Programming", "Data Structures", "Algorithms");
        List<String> resources = Arrays.asList(
                "https://youtube.com/java1",
                "https://youtube.com/oop1",
                "https://geeksforgeeks.org/data-structures",
                "https://javatpoint.com/java-algorithms");

        LearningPath path = new LearningPath();
        path.setStudentId(studentId);
        path.setCareerGoal(career);
        path.setWeeksPlanned(weeks);
        path.setTopics(topics);
        path.setResources(resources);

        return learningPathRepository.save(path);
    }

    
    @GetMapping("/{studentId}")
    public List<LearningPath> getLearningPath(@PathVariable String studentId) {
        return learningPathRepository.findByStudentId(studentId);
    }

    
    @PutMapping("/{studentId}/complete-topic")
    public LearningPath completeTopic(@PathVariable String studentId, @RequestBody Map<String, Object> payload) {
        String topic = (String) payload.get("topic");

        List<LearningPath> paths = learningPathRepository.findByStudentId(studentId);
        if (paths.isEmpty()) {
                throw new RuntimeException("No learning path found for student");
        }

        LearningPath path = paths.get(0); 

        if (path.getCompletedTopics() == null) {
                path.setCompletedTopics(new ArrayList<>());
        }

        if (!path.getCompletedTopics().contains(topic)) {
                path.getCompletedTopics().add(topic);
        }

        if (path.getCompletedTopics().size() == path.getTopics().size()) {
           path.setBadge("🎉 Mastery Badge - Completed All Topics");
        } else if (path.getCompletedTopics().size() >= (path.getTopics().size() / 2)) {
           path.setBadge("🏅 Halfway There!");
        } else {
            path.setBadge(null);
    }


        return learningPathRepository.save(path);
    }
    
    @PatchMapping("/{studentId}/update")
    public LearningPath updateLearningPath(@PathVariable String studentId, @RequestBody Map<String, Object> payload) {
         List<LearningPath> paths = learningPathRepository.findByStudentId(studentId);
         if (paths.isEmpty()) {
                  throw new RuntimeException("No learning path found for student");
         }

         LearningPath path = paths.get(0);

         if (payload.containsKey("careerGoal")) {
                  path.setCareerGoal((String) payload.get("careerGoal"));
         }

         if (payload.containsKey("studyWeeks")) {
                  path.setWeeksPlanned((int) payload.get("studyWeeks"));
         }

        
         path.setCompletedTopics(new ArrayList<>());

         return learningPathRepository.save(path);
}

}
