package com.example.afix.rest_controller.Dto;

import com.example.afix.model.Answer;

import java.util.List;

public record QuestionDto(
        Integer id,
        String question,
        Integer trackId,
        List<AnswerDto> answers
) {}
