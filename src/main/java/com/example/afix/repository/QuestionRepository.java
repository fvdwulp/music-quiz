package com.example.afix.repository;

import com.example.afix.model.Question;
import com.example.afix.model.Song;
import com.example.afix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByOwner(User owner);
    Optional<Question> findByIdAndOwner(Integer id, User owner);
    void deleteByIdAndOwner(Integer id, User owner);

}
