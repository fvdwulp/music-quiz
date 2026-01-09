package com.example.music_quiz.rest_controller;

import com.example.music_quiz.domain.answer.Answer;
import com.example.music_quiz.domain.Question;
import com.example.music_quiz.domain.answer.AnswerRequest;
import com.example.music_quiz.domain.answer.AnswerResponse;
import com.example.music_quiz.service.question.QuestionService;
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
