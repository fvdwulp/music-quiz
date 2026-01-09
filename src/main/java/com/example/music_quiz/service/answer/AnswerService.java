package com.example.music_quiz.service.answer;

import com.example.music_quiz.domain.answer.Answer;

import java.util.List;

public interface AnswerService {
    Answer newAnswer();
    List<Answer> findAll();

}
