package com.example.afix.rest_controller;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.model.Quiz;
import com.example.afix.model.quiz.AnswerRequest;
import com.example.afix.model.quiz.AnswerResponse;
import com.example.afix.service.question.QuestionService;
import com.example.afix.service.quiz.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class QuizRestController {

    QuizService quizService;

    public QuizRestController(
            QuizService quizService
    ) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz/{id}")
    public Quiz questions(@PathVariable UUID id) {
        return quizService.findByString(id);
    }

}
