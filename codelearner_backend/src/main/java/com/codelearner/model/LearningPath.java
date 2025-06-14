package com.codelearner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "learning_paths")
public class LearningPath {

    @Id
    private String id;

    private String studentId;
    private String careerGoal;
    private List<String> topics;
    private List<String> resources;
    private String badge;
    private List<String> completedTopics;


    private int weeksPlanned;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCareerGoal() {
        return careerGoal;
    }

    public void setCareerGoal(String careerGoal) {
        this.careerGoal = careerGoal;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public int getWeeksPlanned() {
        return weeksPlanned;
    }

    public void setWeeksPlanned(int weeksPlanned) {
        this.weeksPlanned = weeksPlanned;
    }

    public List<String> getCompletedTopics() {
        return completedTopics;
   }

    public void setCompletedTopics(List<String> completedTopics) {
       this.completedTopics = completedTopics;
    }

   

   public String getBadge() {
      return badge;
   }

   public void setBadge(String badge) {
      this.badge = badge;
   }


}
