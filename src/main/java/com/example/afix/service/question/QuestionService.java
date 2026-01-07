package com.example.afix.service.question;

import com.example.afix.model.Question;

import java.util.List;

public interface QuestionService {
    Question newQuestion();
    List<Question> findAll();
    Question getById(int id);
    Question getByIdAndOwner(int id);
    Question save(Question question);
    void deleteById(int id);
}
