package com.example.music_quiz.service.quiz;

import com.example.music_quiz.domain.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizService {
    Quiz newQuiz();
    List<Quiz> findAll();
    Quiz findById(UUID id);
    Quiz findByStringAndOwner(UUID id);
    Quiz save(Quiz theQuiz);
    void deleteById(UUID id);
}
