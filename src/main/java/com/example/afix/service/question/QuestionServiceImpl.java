package com.example.afix.service.question;

import com.example.afix.audit.Audit;
import com.example.afix.audit.AuditAction;
import com.example.afix.model.Question;
import com.example.afix.repository.AnswerRepository;
import com.example.afix.repository.QuestionRepository;
import com.example.afix.service.AbstractUserAwareService;
import com.example.afix.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl extends AbstractUserAwareService implements QuestionService{

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;

    @Autowired
    public QuestionServiceImpl(
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            UserService userService
    ) {
        super(userService);
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public Question newQuestion() {
        return new Question();
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findByOwner(getCurrentUser());
    }

    @Override
    public Question getById(int id) {
        Optional<Question> question = questionRepository.findByIdAndOwner(id, getCurrentUser());
        if(question.isPresent())
            return question.get();
        else
            throw new RuntimeException("Did not find question id - " + id);
    }

    @Override
    @Audit(action = AuditAction.QUESTION_UPDATED, entity = "Question")
    public Question save(Question question) {
        question.setOwner(getCurrentUser());
        return questionRepository.save(question);
    }

    @Transactional
    @Override
    @Audit(action = AuditAction.QUESTION_DELETED, entity = "Question")
    public void deleteById(int id) {
        questionRepository.deleteByIdAndOwner(id, getCurrentUser());
    }

}
