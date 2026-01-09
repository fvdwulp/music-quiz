package com.example.music_quiz.controller.dashboard;

import com.example.music_quiz.domain.answer.Answer;
import com.example.music_quiz.domain.Question;
import com.example.music_quiz.service.answer.AnswerService;
import com.example.music_quiz.service.question.QuestionService;
import com.example.music_quiz.service.song.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final SongService songService;
    private final AnswerService answerService;

    public QuestionController(
            QuestionService questionService,
            SongService songService,
            AnswerService answerService
    ){
        this.questionService = questionService;
        this.songService = songService;
        this.answerService = answerService;
    }

    @GetMapping("")
    public String getQuestions(Model model){
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        model.addAttribute("pageTitle", "Vragen");

        return "dashboard/questions/list";
    }

    @GetMapping("/add")
    public String addQuestion(Model model){
        Question question = questionService.newQuestion();
        question.getAnswers().add(answerService.newAnswer());
        question.getAnswers().add(answerService.newAnswer());
        question.getAnswers().add(answerService.newAnswer());

        question.setCorrectAnswerIndex(0);

        model.addAttribute("form", question);
        model.addAttribute("songs", songService.findAll());
        model.addAttribute("pageTitle", "Vraag toevoegen");

        return "dashboard/questions/edit";
    }


    @GetMapping("/edit/{id}")
    public String editQuestion(Model model, @PathVariable int id){
        Question question = questionService.getByIdAndOwner(id);
        if(question == null)
            return "redirect:/questions";

        for (int i = 0; i < question.getAnswers().size(); i++) {
            if (question.getAnswers().get(i).isCorrect()) {
                question.setCorrectAnswerIndex(i);
                break;
            }
        }

        model.addAttribute("form", question);
        model.addAttribute("songs", songService.findAll());
        model.addAttribute("pageTitle", "Vraag aanpassen");

        return "dashboard/questions/edit";
    }

    @PostMapping("/save")
    public String saveQuestion(
            @Valid @ModelAttribute("form") Question question,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("form", question);
            model.addAttribute("songs", songService.findAll());
            model.addAttribute("pageTitle", "Vraag aanpassen");
            return "dashboard/questions/edit";
        }

        for (int i = 0; i < question.getAnswers().size(); i++) {
            Answer a = question.getAnswers().get(i);
            a.setQuestion(question);
            a.setCorrect(i == question.getCorrectAnswerIndex());
        }

        questionService.save(question);

        return "redirect:/questions";
    }

    @PostMapping("/{id}/delete")
    public String deleteQuestion(@PathVariable int id){
        Question question = questionService.getByIdAndOwner(id);
        if(question == null)
            return "redirect:/questions";

        questionService.deleteById(id);

        return "redirect:/questions";
    }
}
