package com.example.afix.service.question;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.model.Song;
import com.example.afix.model.quiz.AnswerRequest;
import com.example.afix.model.quiz.AnswerResponse;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();
    Question getById(int id);
    Answer answer(AnswerRequest answer);
    Question save(Question theQuestion);
    void deleteById(int id);
}
