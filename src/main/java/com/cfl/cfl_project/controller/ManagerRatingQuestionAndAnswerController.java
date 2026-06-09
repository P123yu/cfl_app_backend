package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.dto.QuestionAndAnswerDTO;
import com.cfl.cfl_project.model.ManagerRatingQuestionAndAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cfl.cfl_project.service.ManagerRatingQuestionAndAnswerService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/question-answer")
public class ManagerRatingQuestionAndAnswerController {

    @Autowired
    private ManagerRatingQuestionAndAnswerService managerRatingQuestionAndAnswerService;

    @PostMapping("/create/{empId}")
    public ResponseEntity<?> createQuestionAndAnswer(@PathVariable Long empId,@RequestBody List<ManagerRatingQuestionAndAnswer> managerRatingQuestionAndAnswer) {
        System.out.println(empId+" "+managerRatingQuestionAndAnswer);
        List<ManagerRatingQuestionAndAnswer> managerRatingQuestionAndAnswerObj=managerRatingQuestionAndAnswerService.saveQuestionAndAnswer(empId,managerRatingQuestionAndAnswer);
        if(!managerRatingQuestionAndAnswer.isEmpty()){
            return ResponseEntity.ok(managerRatingQuestionAndAnswerObj);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create ManagerRatingQuestionAndAnswer");
        }
    }


    @GetMapping("/getByEmpId/{empId}")
    public ResponseEntity<?> getQuestionAndAnswerByEmpId(@PathVariable Long empId){
        QuestionAndAnswerDTO questionAndAnswerDTO=managerRatingQuestionAndAnswerService.getQuestionAndAnswer(empId);
        if(questionAndAnswerDTO !=null ){
            return ResponseEntity.ok(questionAndAnswerDTO);
        }
        else{
            return ResponseEntity.status(400).body("Failed to get ManagerRatingQuestionAndAnswer");
        }
    }

}
