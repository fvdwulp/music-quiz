package com.example.afix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping({"", "/{id}"})
    public String quiz(@PathVariable UUID id, Model model) {
        model.addAttribute("quizId", id);
        return "quiz";
    }

}
