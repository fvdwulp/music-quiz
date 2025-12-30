package com.example.afix.service.question;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.model.Song;
import com.example.afix.model.quiz.AnswerRequest;
import com.example.afix.model.quiz.AnswerResponse;

import java.util.List;

public interface QuestionService {
    Question newQuestion();
    List<Question> findAll();
    Question getById(int id);
    Answer answer(AnswerRequest answer);
    Question save(Question question);
    void deleteById(int id);
}
