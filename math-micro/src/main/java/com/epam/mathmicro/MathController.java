package com.epam.mathmicro;

import org.HdrHistogram.SynchronizedHistogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgeny Borisov
 */
@RestController
@RequestMapping("/api")
public class MathController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getRandomQuestions(@RequestParam ("amount") int amount) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(questionService.getRandomQuestion());

        }
        return questions;
    }
}
