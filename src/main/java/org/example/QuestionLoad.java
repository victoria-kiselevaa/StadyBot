package org.example;

import java.io.IOException;
import java.util.List;

public interface QuestionLoad {
    List<Question> loadQuestions(String file) throws IOException;
}
