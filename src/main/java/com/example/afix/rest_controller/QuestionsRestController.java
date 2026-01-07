package com.example.afix.rest_controller;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.model.quiz.AnswerRequest;
import com.example.afix.model.quiz.AnswerResponse;
import com.example.afix.service.question.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Question question = questionService.getById(answerRequest.questionId);
        Answer correctAnswer = question.getAnswers().stream().filter(Answer::isCorrect).findFirst().orElse(null);
        boolean isCorrect = correctAnswer != null && correctAnswer.getId() == answerRequest.answerId;

        return ResponseEntity.ok(new AnswerResponse(isCorrect));
    }

}
