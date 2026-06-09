package com.cfl.cfl_project.service;

import com.cfl.cfl_project.dto.QuestionAndAnswerDTO;
import com.cfl.cfl_project.model.ManagerRatingQuestionAndAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerRatingQuestionAndAnswerService {
    List<ManagerRatingQuestionAndAnswer> saveQuestionAndAnswer(Long empId, List<ManagerRatingQuestionAndAnswer> managerRatingQuestionAndAnswerList);
    QuestionAndAnswerDTO getQuestionAndAnswer(Long empId);
}
