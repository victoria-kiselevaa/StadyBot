package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    //вместо звездочек подставляйте свои данные

    final private String BOT_TOKEN = "7366036331:AAGAqt69yIHqOPErB9g47GO0g1gfYMHrJzE";
    final private String BOT_NAME = "Stady_Examinator_bot";

    Storage storage;

//    Bot() {
//        storage = new Storage();
//    }

    private List<Question> questions;
    private Examinator examinor;
    private boolean isExamMode = false; // Флаг, указывающий режим экзамена
    public Bot() {
        try {
            questions = new TxtQuestionLoader().loadQuestions("questions.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {return BOT_TOKEN; }

//    public String parseMessage(String textMsg) {
//        String response;
//        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
//        if(textMsg.equals("/start"))
//            response = "Привет";
//        else if(textMsg.equals("/get"))
//            response = storage.getRandQuote();
//        else
//            response = "Сообщение не распознано";
//        return response;
//    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
//            if(update.hasMessage() && update.getMessage().hasText())
//            {
//                //Извлекаем из объекта сообщение пользователя
//                Message inMess = update.getMessage();
//                //Достаем из inMess id чата пользователя
//                String chatId = inMess.getChatId().toString();
//                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
//                String response = parseMessage(inMess.getText());
//                //Создаем объект класса SendMessage - наш будущий ответ пользователю
//                SendMessage outMess = new SendMessage();
//                //Добавляем в наше сообщение id чата а также наш ответ
//                outMess.setChatId(chatId);
//                outMess.setText(response);
//                //Отправка в чат
//                execute(outMess);
//            }
            if (update.hasMessage() && update.getMessage().hasText()) {
                String chatId = update.getMessage().getChatId().toString();
                String userMessage = update.getMessage().getText();
                String response = ""; // Переменная для ответа

                if (userMessage.equals("/exam")) {
                    isExamMode = true; // Включаем режим экзамена
                    examinor = new Examinator(new ArrayList<>(questions)); // Создаем новый экземпляр Examinator
                    Question firstQuestion = examinor.action(); // Получаем первый вопрос

                    if (firstQuestion != null) {
                        response = firstQuestion.getQuestionText(); // Отправить первый вопрос
                    } else {
                        response = "Вопросы не загружены. Пожалуйста, проверьте файл.";
                        isExamMode = false; // Выключаем экзамен, если вопросов нет
                    }
                } else if (isExamMode && examinor != null) {
                    boolean isCorrect = examinor.check(userMessage, examinor.action()); // Проверяем ответ

                    if (isCorrect) {
                        response = "Правильно!";
                    } else {
                        response = "Неправильно. Правильный ответ: " + examinor.action().getCorrectAnswer();
                    }

                    Question nextQuestion = examinor.action(); // Получаем следующий вопрос
                    if (nextQuestion != null) {
                        response += "\n" + nextQuestion.getQuestionText(); // Отправляем следующий вопрос
                    } else {
                        response += "\nЭкзамен завершен. " + examinor.end(); // Завершение экзамена и подведение итогов
                        isExamMode = false; // Выход из режима экзамена
                    }
                } else {
                    response = "Команда не распознана. Используйте /exam для начала экзамена.";
                }

                SendMessage outMess = new SendMessage();
                outMess.setChatId(chatId);
                outMess.setText(response);
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

