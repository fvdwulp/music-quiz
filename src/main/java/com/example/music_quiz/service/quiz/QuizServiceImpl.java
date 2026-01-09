package com.example.music_quiz.service.quiz;

import com.example.music_quiz.audit.Audit;
import com.example.music_quiz.audit.AuditAction;
import com.example.music_quiz.domain.Quiz;
import com.example.music_quiz.repository.QuizRepository;
import com.example.music_quiz.service.AbstractUserAwareService;
import com.example.music_quiz.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizServiceImpl extends AbstractUserAwareService implements QuizService {

    QuizRepository quizRepository;

    public QuizServiceImpl(
            QuizRepository quizRepository,
            UserService userService
    ) {
        super(userService);
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz newQuiz() {
        return new Quiz();
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findByOwner(getCurrentUser());
    }

    @Override
    public Quiz findById(UUID id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if(quiz.isPresent())
            return quiz.get();
        else
            throw new RuntimeException("Did not find quiz id - " + id);
    }

    @Override
    public Quiz findByStringAndOwner(UUID id) {
        Optional<Quiz> quiz = quizRepository.findByIdAndOwner(id, getCurrentUser());
        if(quiz.isPresent())
            return quiz.get();
        else
            throw new RuntimeException("Did not find quiz id - " + id);
    }

    @Override
    @Audit(action = AuditAction.QUIZ_UPDATED, entity = "Quiz")
    public Quiz save(Quiz theQuiz) {
        theQuiz.setOwner(getCurrentUser());
        return quizRepository.save(theQuiz);
    }

    @Transactional
    @Override
    @Audit(action = AuditAction.QUIZ_DELETED, entity = "Quiz")
    public void deleteById(UUID id) {
        quizRepository.deleteByIdAndOwner(id, getCurrentUser());
    }
}
