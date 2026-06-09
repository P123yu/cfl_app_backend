package com.cfl.cfl_project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class QuestionAndAnswerDTO {
    private List<String> question;
    private List<String> answer;
}
