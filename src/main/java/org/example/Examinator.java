package org.example;

import java.util.List;
import java.util.Random;

public class Examinator {
    private List<Question> questions;
    private int correctAnswersCount = 0;
    private int totalQuestions = 0;

    public Examinator(List<Question> questions) {
        this.questions = questions;
    }

    public Question action() {
        Random rand = new Random();
        if (questions.isEmpty()) return null;
        return questions.remove(rand.nextInt(questions.size()));
    }

    public boolean check(String answer, Question question) {
        totalQuestions++;
        if (question.getCorrectAnswer().equalsIgnoreCase(answer.trim())) {
            correctAnswersCount++;
            return true;
        }
        return false;
    }

    public String end() {
        return "Тест завершен. Правильных ответов: " + correctAnswersCount + "/" + totalQuestions;
    }
}