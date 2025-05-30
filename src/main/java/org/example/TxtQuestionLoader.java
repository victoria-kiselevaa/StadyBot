package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtQuestionLoader implements QuestionLoad {
    @Override
    public List<Question> loadQuestions(String filePath) throws IOException {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|"); // использование символа | в качестве разделителя
                if (parts.length == 2) { // Проверяем, что в строке два элемента
                    questions.add(new Question(parts[0].trim(), parts[1].trim()));
                }
            }
        }
        return questions;
    }
}