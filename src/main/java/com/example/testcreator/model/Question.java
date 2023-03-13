package com.example.testcreator.model;

import lombok.Data;
import java.util.List;

@Data
public class Question {

    private String questionText;

    private final List<Answer> answers = List.of(new Answer(), new Answer(), new Answer(), new Answer());
}
