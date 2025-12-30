package com.example.afix.service.quiz;

import com.example.afix.model.Quiz;
import com.example.afix.model.Song;
import com.example.afix.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz newQuiz() {
        return new Quiz();
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz findByString(UUID id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if(quiz.isPresent())
            return quiz.get();
        else
            throw new RuntimeException("Did not find quiz id - " + id);
    }

    @Override
    public Quiz save(Quiz theQuiz) {
        return quizRepository.save(theQuiz);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        quizRepository.deleteById(id);
    }
}
