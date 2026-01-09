package com.example.music_quiz.repository;


import com.example.music_quiz.domain.Quiz;
import com.example.music_quiz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> findByOwner(User owner);
    Optional<Quiz> findByIdAndOwner(UUID id, User owner);
    void deleteByIdAndOwner(UUID id, User owner);
}
