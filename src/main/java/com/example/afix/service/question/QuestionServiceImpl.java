package com.example.afix.service.question;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.model.Song;
import com.example.afix.model.quiz.AnswerRequest;
import com.example.afix.model.quiz.AnswerResponse;
import com.example.afix.repository.AnswerRepository;
import com.example.afix.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;

    @Autowired
    public QuestionServiceImpl(
            QuestionRepository questionRepository,
            AnswerRepository answerRepository
    ) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question getById(int id) {

        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent())
            return question.get();
        else
            throw new RuntimeException("Did not find question id - " + id);
    }

    @Override
    public Answer answer(AnswerRequest answer) {
        return answerRepository.findByQuestionIdAndIsCorrectTrue(answer.questionId);
    }

    @Override
    public Question save(Question theQuestion) {
        return questionRepository.save(theQuestion);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

}
