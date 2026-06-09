package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.Question;
import com.cfl.cfl_project.repository.QuestionRepository;
import com.cfl.cfl_project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> createListOfQuestion(List<Question> questionList) {
        return questionRepository.saveAll(questionList);
    }

    @Override
    public List<Question> findAllQuestionBasedOnPotentialAndPerformance(int potential, int performance) {
        return questionRepository.findByPotentialAndPerformance(potential,performance);
    }
}
