package com.example.testcreator.controller;

import com.example.testcreator.model.Question;
import com.example.testcreator.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/create")
    public String createQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "question-create";
    }

    @PostMapping("/add")
    public String questionAdd(Question question) {
        System.out.println(question);
        questionService.createQuestion(question);
        return "redirect:/question/create";
    }
}
