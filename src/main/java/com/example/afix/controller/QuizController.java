package com.example.afix.controller;


import com.example.afix.model.Quiz;
import com.example.afix.service.question.QuestionService;
import com.example.afix.service.quiz.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class QuizController {

    QuizService quizService;
    QuestionService questionService;

    public QuizController(
            QuizService quizService,
            QuestionService questionService
    ) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping({"/quiz", "/quiz/{id}"})
    public String quiz(@PathVariable UUID id, Model model) {
        model.addAttribute("quizId", id);
        return "quiz";
    }

    @GetMapping("/quizzes")
    public String quizzes(Model model) {
        List<Quiz> allQuizzes = quizService.findAll();
        model.addAttribute("quizzes", allQuizzes);
        model.addAttribute("pageTitle", "Quizen");
        return "dashboard/quizzes/list";
    }

    @GetMapping("/quizzes/add")
    public String addQuiz(Model model){
        Quiz newQuiz = new Quiz();
        model.addAttribute("quiz", newQuiz);
        model.addAttribute("pageTitle", "Quiz toevoegen");
        return "dashboard/quizzes/add";
    }

    @GetMapping("/quizzes/edit/{id}")
    public String editQuiz(@PathVariable UUID id, Model model) {
        Quiz existingQuiz = quizService.findByString(id);
        if (existingQuiz == null)
            return "redirect:/quizzes";

        var allQuestions = questionService.findAll();
        model.addAttribute("quiz", existingQuiz);
        model.addAttribute("allQuestions", allQuestions);
        model.addAttribute("pageTitle", "Quiz aanpassen");
        return "dashboard/quizzes/edit";
    }

    @PostMapping("/quizzes/save")
    public String saveQuiz(@ModelAttribute Quiz theQuiz){
        quizService.save(theQuiz);

        return "redirect:/quizzes";
    }

    @GetMapping("/quizzes/delete")
    public String deleteQuiz(@RequestParam UUID id){
        Quiz quiz = quizService.findByString(id);
        if(quiz == null)
            return "redirect:/quizzes";

        quizService.deleteById(id);

        return "redirect:/quizzes";
    }
}
