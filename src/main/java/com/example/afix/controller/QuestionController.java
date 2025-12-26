package com.example.afix.controller;

import com.example.afix.model.Answer;
import com.example.afix.model.Question;
import com.example.afix.service.question.QuestionService;
import com.example.afix.service.song.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuestionController {

    QuestionService questionService;
    SongService songService;

    public QuestionController(
            QuestionService questionService,
            SongService songService
    ){
        this.questionService = questionService;
        this.songService = songService;
    }

    @GetMapping("/questions")
    public String getQuestions(Model model){
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        model.addAttribute("pageTitle", "Vragen");

        return "dashboard/questions/list";
    }

    @GetMapping("/questions/add")
    public String addQuestion(Model model){
        Question newQuestion = new Question();
        newQuestion.getAnswers().add(new Answer());
        newQuestion.getAnswers().add(new Answer());
        newQuestion.getAnswers().add(new Answer());

        model.addAttribute("question", newQuestion);
        model.addAttribute("songs", songService.findAll());
        model.addAttribute("pageTitle", "Vraag toevoegen");

        return "dashboard/questions/edit";
    }


    @GetMapping("/questions/edit/{id}")
    public String editQuestion(Model model, @PathVariable int id){

        Question question = questionService.getById(id);
        model.addAttribute("question", question);
        model.addAttribute("songs", songService.findAll());
        model.addAttribute("pageTitle", "Vraag aanpassen");

        return "dashboard/questions/edit";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(@ModelAttribute("theQuestion") Question theQuestion) {

        theQuestion.getAnswers().removeIf(a -> a.getAnswer() == null || a.getAnswer().isBlank());
        for (Answer a : theQuestion.getAnswers()) {
            a.setQuestion(theQuestion);
        }

        questionService.save(theQuestion);

        return "redirect:/questions";
    }

    @GetMapping("/questions/delete")
    public String deleteQuestion(@RequestParam int id){

        Question question = questionService.getById(id);
        if(question == null)
            return "redirect:/questions";

        questionService.deleteById(id);

        return "redirect:/questions";

    }
}
