package com.example.music_quiz.audit;

public enum AuditAction {
    SONG_CREATED,
    SONG_UPDATED,
    SONG_DELETED,

    QUIZ_CREATED,
    QUIZ_UPDATED,
    QUIZ_DELETED,

    USER_UPDATED,
    USER_DELETED,

    QUESTION_UPDATED,
    QUESTION_DELETED,

    LOGIN,
    LOGOUT
}
