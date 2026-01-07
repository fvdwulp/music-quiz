package com.example.afix.rest_controller;

import com.example.afix.rest_controller.Dto.AnswerDto;
import com.example.afix.rest_controller.Dto.QuestionDto;
import com.example.afix.rest_controller.Dto.QuizDto;
import com.example.afix.service.quiz.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
    public QuizDto questions(@PathVariable UUID id) {
        var quiz = quizService.findById(id);

        return new QuizDto(
                quiz.getId(),
                quiz.getEnabled(),
                quiz.getTitle(),
                quiz.getQuestions().stream()
                    .map(q -> {
                        List<AnswerDto> answers = q.getAnswers().stream()
                                .map(a -> new AnswerDto(a.getId(), a.getAnswer()))
                                .collect(Collectors.toList());
                        Collections.shuffle(answers);

                        return new QuestionDto(
                            q.getId(),
                                q.getQuestion(),
                                q.getSong().getTrackId(),
                                answers
                        );
                    }).toList()
        );
    }

}

