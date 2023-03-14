package com.example.testcreator.service;

import com.example.testcreator.model.Question;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@Validated
public class QuestionService {
    @Value("${my.file.name}")
    private String fileName;

    public void writeQuestion(@Valid Question question) {
        StringBuilder builder = new StringBuilder(readFile());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String LEFT_BRACKET = "[";
        final String RIGHT_BRACKET = "]";
        try (FileWriter writer = new FileWriter(fileName)) {
            if (builder.isEmpty()) {
                writer.write(LEFT_BRACKET + gson.toJson(question) + RIGHT_BRACKET);
            } else {
                int start = builder.indexOf(LEFT_BRACKET);
                int end = builder.lastIndexOf(RIGHT_BRACKET);
                String writeString = LEFT_BRACKET + builder.substring(start + 1, end) + ",\n" +
                        gson.toJson(question) + RIGHT_BRACKET;
                writer.write(writeString);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private String readFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(new File(fileName));
            return node.toPrettyString();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
