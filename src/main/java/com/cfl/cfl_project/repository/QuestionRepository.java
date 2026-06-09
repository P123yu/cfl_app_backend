package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByPotentialAndPerformance(int potential,int performance);
    List<Question> findByQuestionIdIn(List<Long>questionIdList);
}
