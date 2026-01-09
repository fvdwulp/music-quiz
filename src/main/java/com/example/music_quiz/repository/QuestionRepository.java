package com.example.music_quiz.repository;

import com.example.music_quiz.domain.Question;
import com.example.music_quiz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByOwner(User owner);
    Optional<Question> findByIdAndOwner(Integer id, User owner);
    void deleteByIdAndOwner(Integer id, User owner);

}
