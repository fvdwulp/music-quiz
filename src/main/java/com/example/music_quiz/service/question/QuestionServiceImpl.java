package com.example.music_quiz.service.question;

import com.example.music_quiz.audit.Audit;
import com.example.music_quiz.audit.AuditAction;
import com.example.music_quiz.domain.Question;
import com.example.music_quiz.repository.AnswerRepository;
import com.example.music_quiz.repository.QuestionRepository;
import com.example.music_quiz.service.AbstractUserAwareService;
import com.example.music_quiz.service.user.UserService;
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
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent())
            return question.get();
        else
            throw new RuntimeException("Did not find question id - " + id);
    }

    @Override
    public Question getByIdAndOwner(int id) {
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
