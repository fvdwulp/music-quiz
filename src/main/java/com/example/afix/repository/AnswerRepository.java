package com.example.afix.repository;

import com.example.afix.model.Answer;
import com.example.afix.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    
}
