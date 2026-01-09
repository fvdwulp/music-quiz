package com.example.music_quiz.controller.dashboard;


import com.example.music_quiz.domain.Quiz;
import com.example.music_quiz.service.question.QuestionService;
import com.example.music_quiz.service.quiz.QuizService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/quizzes")
public class AdminQuizController {

    private final QuizService quizService;
    private final QuestionService questionService;

    public AdminQuizController(
            QuizService quizService,
            QuestionService questionService
    ) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping("")
    public String quizzes(Model model) {
        List<Quiz> allQuizzes = quizService.findAll();
        model.addAttribute("quizzes", allQuizzes);
        model.addAttribute("pageTitle", "Quizen");
        return "dashboard/quizzes/list";
    }

    @GetMapping("/add")
    public String addQuiz(Model model){
        Quiz quiz = quizService.newQuiz();
        model.addAttribute("quiz", quiz);
        addAllQuestions(model);
        model.addAttribute("pageTitle", "Quiz toevoegen");
        return "dashboard/quizzes/edit";
    }

    @GetMapping("/edit/{id}")
    public String editQuiz(@PathVariable UUID id, Model model) {
        Quiz existingQuiz = quizService.findByStringAndOwner(id);
        if (existingQuiz == null)
            return "redirect:/quizzes";

        model.addAttribute("quiz", existingQuiz);
        addAllQuestions(model);
        model.addAttribute("pageTitle", "Quiz aanpassen");
        return "dashboard/quizzes/edit";
    }

    @PostMapping("/save")
    public String saveQuiz(
            @Valid @ModelAttribute Quiz quiz,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            model.addAttribute("quiz", quiz);
            addAllQuestions(model);
            model.addAttribute("pageTitle", quiz.getId() == null ? "Quiz toevoegen" : "Quiz aanpassen");
            return "dashboard/quizzes/edit";
        }

        quizService.save(quiz);

        return "redirect:/quizzes";
    }

    @PostMapping("/{id}/delete")
    public String deleteQuiz(@PathVariable UUID id){
        Quiz quiz = quizService.findByStringAndOwner(id);
        if(quiz == null)
            return "redirect:/quizzes";

        quizService.deleteById(id);

        return "redirect:/quizzes";
    }

    private void addAllQuestions(Model model){
        model.addAttribute("allQuestions", questionService.findAll());
    }
}
