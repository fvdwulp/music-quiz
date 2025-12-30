package com.example.afix.service.quiz;

import com.example.afix.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface QuizService {
    Quiz newQuiz();
    List<Quiz> findAll();
    Quiz findByString(UUID id);
    Quiz save(Quiz theQuiz);
    void deleteById(UUID id);
}
