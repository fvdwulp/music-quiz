package com.example.afix.rest_controller;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.service.answer.AnswerService;
import com.example.afix.service.question.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswersRestController {

    AnswerService answerService;

    public AnswersRestController(
            AnswerService answerService
    ) {
        this.answerService = answerService;
    }



}
