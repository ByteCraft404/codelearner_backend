package com.codelearner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")


public class Student {

    @Id
    private String id;

    private String fullName;
    private String email;
    private String password;
    private String careerGoal;
    private int studyHoursPerWeek;
    private String[] learningPreferences;
    private String[] grades;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCareerGoal() {
        return careerGoal;
    }

    public void setCareerGoal(String careerGoal) {
        this.careerGoal = careerGoal;
    }

    public int getStudyHoursPerWeek() {
        return studyHoursPerWeek;
    }

    public void setStudyHoursPerWeek(int studyHoursPerWeek) {
        this.studyHoursPerWeek = studyHoursPerWeek;
    }

    public String[] getLearningPreferences() {
        return learningPreferences;
    }

    public void setLearningPreferences(String[] learningPreferences) {
        this.learningPreferences = learningPreferences;
    }

    public String[] getGrades() {
        return grades;
    }

    public void setGrades(String[] grades) {
        this.grades = grades;
    }
}
