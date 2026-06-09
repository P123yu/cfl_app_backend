package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.ManagerRatingQuestionAndAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRatingQuestionAndAnswerRepository extends JpaRepository<ManagerRatingQuestionAndAnswer,Long> {
    List<ManagerRatingQuestionAndAnswer> findByEmpId(Long empId);
}
