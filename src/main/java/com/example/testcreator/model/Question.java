package com.example.testcreator.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class Question {

    @NotBlank
    private String questionText;

    @Valid
    private final List<Answer> answers = List.of(new Answer(), new Answer(), new Answer(), new Answer());
}
