package com.example.testcreator.service;

import com.example.testcreator.model.Question;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class QuestionService {
    @Value("${my.file.name}")
    private String fileName;

    public void createQuestion(Question question) {
        System.out.println(fileName);
        try {
            writeFile(question);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(Question question) throws IOException {
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
        }
    }

    private String readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new File(fileName));
        return node.toPrettyString();
    }
}
