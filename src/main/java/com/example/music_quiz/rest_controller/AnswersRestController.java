package com.example.music_quiz.rest_controller;

import com.example.music_quiz.service.answer.AnswerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
