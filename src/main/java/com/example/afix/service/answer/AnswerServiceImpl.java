package com.example.afix.service.answer;

import com.example.afix.model.Answer;
import com.example.afix.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;

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
