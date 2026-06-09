package com.cfl.cfl_project.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int questionId;
    @Column(columnDefinition = "Text")
    private String question;
    private int potential;
    private int performance;
}
