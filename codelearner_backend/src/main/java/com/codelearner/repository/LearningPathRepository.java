package com.codelearner.repository;

import com.codelearner.model.LearningPath;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LearningPathRepository extends MongoRepository<LearningPath, String> {
    List<LearningPath> findByStudentId(String studentId);
}
