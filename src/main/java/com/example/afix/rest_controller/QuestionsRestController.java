package com.example.afix.rest_controller;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.model.Song;
import com.example.afix.model.quiz.AnswerRequest;
import com.example.afix.model.quiz.AnswerResponse;
import com.example.afix.service.question.QuestionService;
import com.example.afix.service.song.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionsRestController {

    QuestionService questionService;

    public QuestionsRestController(
            QuestionService questionService
    ) {
        this.questionService = questionService;
    }

    @PostMapping("/questions/check")
    public ResponseEntity<AnswerResponse> checkAnswer(@RequestBody AnswerRequest answerRequest) {

        Answer correctAnswer = questionService.answer(answerRequest);
        boolean isCorrect = correctAnswer != null && correctAnswer.getId() == answerRequest.answerId;

        return ResponseEntity.ok(new AnswerResponse(isCorrect));
    }

}
