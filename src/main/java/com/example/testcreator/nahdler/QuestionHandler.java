package com.example.testcreator.nahdler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class QuestionHandler {

    @ExceptionHandler
    public String handler(TemplateInputException e) {
        return e.getMessage();
    }
}
