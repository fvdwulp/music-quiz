package com.example.music_quiz.dto;

import java.util.List;

public record QuestionDto(
        Integer id,
        String question,
        Integer trackId,
        List<AnswerDto> answers
) {}
