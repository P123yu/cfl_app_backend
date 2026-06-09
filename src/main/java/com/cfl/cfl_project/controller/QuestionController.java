package com.cfl.cfl_project.controller;


import com.cfl.cfl_project.model.Question;
import com.cfl.cfl_project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @PostMapping("/create-all")
    public ResponseEntity<List<Question>> createListOfQuestion(@RequestBody List<Question> questionList) {
        List<Question> questions=questionService.createListOfQuestion(questionList);
        return !questions.isEmpty() ? ResponseEntity.ok(questions) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Question>> findAllQuestionBasedOnPotentialAndPerformance(@RequestParam int potential, @RequestParam int performance) {
        List<Question>questionList=questionService.findAllQuestionBasedOnPotentialAndPerformance(potential,performance);
        return !questionList.isEmpty() ? ResponseEntity.ok(questionList) : ResponseEntity.notFound().build();
    }
}
