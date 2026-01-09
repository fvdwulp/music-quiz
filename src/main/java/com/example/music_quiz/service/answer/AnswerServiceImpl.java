package com.example.music_quiz.service.answer;

import com.example.music_quiz.domain.answer.Answer;
import com.example.music_quiz.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(
            AnswerRepository answerRepository
    ) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer newAnswer() {
        return new Answer();
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

}
