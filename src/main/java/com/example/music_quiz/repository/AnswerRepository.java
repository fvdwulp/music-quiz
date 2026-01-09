package com.example.music_quiz.repository;

import com.example.music_quiz.domain.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
