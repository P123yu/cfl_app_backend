package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    List<Question> createListOfQuestion(List<Question> questionList);

    List<Question> findAllQuestionBasedOnPotentialAndPerformance(int potential,int performance);

}
