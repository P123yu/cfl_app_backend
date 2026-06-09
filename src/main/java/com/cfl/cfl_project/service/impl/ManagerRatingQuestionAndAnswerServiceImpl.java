package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.dto.QuestionAndAnswerDTO;
import com.cfl.cfl_project.email.CflToMentorMail;
import com.cfl.cfl_project.model.Cfl;
import com.cfl.cfl_project.model.ManagerRatingQuestionAndAnswer;
import com.cfl.cfl_project.model.Question;
import com.cfl.cfl_project.repository.CflRepository;
import com.cfl.cfl_project.repository.QuestionRepository;
import com.cfl.cfl_project.service.ManagerRatingQuestionAndAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.cfl.cfl_project.repository.ManagerRatingQuestionAndAnswerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ManagerRatingQuestionAndAnswerServiceImpl implements ManagerRatingQuestionAndAnswerService {

    public static String getQuarter() {
        LocalDate date=LocalDate.now();
        int month = date.getMonthValue();

        if (month >= 1 && month <= 3) {
            return "Q4";
        } else if (month >= 4 && month <= 6) {
            return "Q1";
        } else if (month >= 7 && month <= 9) {
            return "Q2";
        } else if (month >= 10 && month <= 12) {
            return "Q3";
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    @Autowired
    private ManagerRatingQuestionAndAnswerRepository managerRatingQuestionAndAnswerRepository;


    @Autowired
    private QuestionRepository questionRepository;

//    @Override
//    public ManagerRatingQuestionAndAnswer saveQuestionAndAnswer(ManagerRatingQuestionAndAnswer managerRatingQuestionAndAnswer) {
//        managerRatingQuestionAndAnswer.setQuarter(getQuarter());
//        return managerRatingQuestionAndAnswerRepository.save(managerRatingQuestionAndAnswer);
//    }

    @Autowired
    private CflRepository cflRepository;

    @Autowired
    private CflToMentorMail cflToMentorMail;


    @Override
    @Async
    @Transactional
    public List<ManagerRatingQuestionAndAnswer> saveQuestionAndAnswer(Long empId,List<ManagerRatingQuestionAndAnswer> managerRatingQuestionAndAnswerList) {
        if (managerRatingQuestionAndAnswerList.isEmpty()) {
            throw new IllegalArgumentException("managerRatingQuestionAndAnswer cannot be empty");
        }


        List<ManagerRatingQuestionAndAnswer>managerRatingQuestionAndAnswerLists=managerRatingQuestionAndAnswerList.stream()
                .peek(i->i.setEmpId(empId)).toList();


        // fetch a list of answers

        List<String> answerList=managerRatingQuestionAndAnswerList.stream().sorted(Comparator.comparing(ManagerRatingQuestionAndAnswer::getQuestionId))
                .map(ManagerRatingQuestionAndAnswer::getAnswer).toList();

        // fetch a list if questions

        List<Long> listOfQuestionId= managerRatingQuestionAndAnswerLists.stream().map(ManagerRatingQuestionAndAnswer::getQuestionId).sorted().toList();

        List<String> questionList= questionRepository.findByQuestionIdIn(listOfQuestionId)
                .stream().sorted(Comparator.comparing(Question::getQuestionId))
                .map(Question::getQuestion).toList();


//        Cfl cfl = cflRepository.findByEmpId(empId);
//        if (cfl == null) {
//            throw new IllegalStateException("No CFL record found for employee ID: " + empId);
//        }



        // Ensure question answers are not null to avoid sending null values in the email
        String q1 =questionList.get(0);
        String q2 =questionList.get(1);
        String q3 =questionList.get(2);
        String q4 =questionList.get(3);
        String q5 =questionList.get(4);



        String a1 =answerList.get(0);
        String a2 =answerList.get(1);
        String a3 =answerList.get(2);
        String a4 =answerList.get(3);
        String a5 =answerList.get(4);



//
//        cflToMentorMail.sendManagerQuestionAnswerMailToHR(
//                cfl.getHrMail(), cfl.getCflFirstName(),
//                q1, a1, q2, a2, q3, a3, q4, a4, q5, a5,  cfl.getReportingManager()
//        );

        return managerRatingQuestionAndAnswerRepository.saveAll(managerRatingQuestionAndAnswerLists);
    }



    @Override
    public QuestionAndAnswerDTO getQuestionAndAnswer(Long empId) {
        List<ManagerRatingQuestionAndAnswer> managerRatingQuestionAndAnswerList= managerRatingQuestionAndAnswerRepository.findByEmpId(empId);
        List<Long>questionId=managerRatingQuestionAndAnswerList.stream().map(ManagerRatingQuestionAndAnswer::getQuestionId).toList();
        List<String> questions=questionRepository.findByQuestionIdIn(questionId)
                .stream().sorted(Comparator.comparing(Question::getQuestionId))
                .map(Question::getQuestion)
                .toList();

        List<String> answers=managerRatingQuestionAndAnswerList.stream()
                .sorted(Comparator.comparing(ManagerRatingQuestionAndAnswer::getQuestionId))
                .map(ManagerRatingQuestionAndAnswer::getAnswer)
                .toList();

        return QuestionAndAnswerDTO.builder().question(questions).answer(answers).build();

    }
}
