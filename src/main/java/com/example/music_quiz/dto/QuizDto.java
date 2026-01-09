package com.example.music_quiz.dto;

import java.util.List;
import java.util.UUID;

public record QuizDto(
        UUID id,
        Boolean enabled,
        String title,
        List<QuestionDto> questions
) {}
