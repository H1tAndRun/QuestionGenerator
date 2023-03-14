package com.example.testcreator.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Answer {

    @NotBlank
    private String answerText;

    private boolean isRight;
}
