package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetQuestion {
    private List<Question> questions;

    public SetQuestion() {
        this.questions = new ArrayList<>();
    }

    // Метод для загрузки вопросов
    public void loadQuestions(QuestionLoad loader, String sourceFile) throws IOException {
        this.questions = loader.loadQuestions(sourceFile);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public boolean hasQuestions() {
        return !questions.isEmpty();
    }
}
