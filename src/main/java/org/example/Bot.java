package org.example;

import org.example.question.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {

    private HashMap<Long, UserData> users;
    private ArrayList<AbstractQuestion> questions;

    public Bot() {
        users = new HashMap<>();
        questions = new ArrayList<>();
        questions.add(new JavaQuustion());
        questions.add(new SQLQuestion());
        questions.add(new GitQuestion());
        questions.add(new HttpQuestion());
    }

    @Override
    public String getBotUsername() {
        return "AlexSpecialTestBot";
    }

    @Override
    public String getBotToken() {
        return "6785922320:AAGl8nGOv3ljhwN3naOzCyyZd0uYQfiFnlI";
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        long userId = message.getFrom().getId();

        if (text.equals("/start")) {
            sendText(userId, "Привет! Это тест навыков" +
                    "по JAVA. И так, начнем )))");
            users.put(userId, new UserData());
            String question = questions.get(0).getQuestion();
            sendText(userId, question);
        } else if (users.get(userId).getQuestionNumber() >= questions.size() - 1) {
            sendText(userId, "Ваш рейтиг: " + users.get(userId).getScore() + " Из " +
                    questions.size());
            sendText(userId, "Тест завершён. Для перезапуска бота используйте команду /start");
        } else {
            UserData userData = users.get(userId);
            int questionNumber = userData.getQuestionNumber();
            boolean result = questions.get(questionNumber).checkAnswer(text);
            int score = userData.getScore();
            userData.setScore(score + (result ? 1 : 0));
            userData.setQuestionNumber(questionNumber + 1);
//            if (questionNumber + 1 == questions.size()) {
//
//            } else {
//                String question = questions.get(userData.getQuestionNumber()).getQuestion();
//                sendText(userId, question);
//            }
            String question = questions.get(userData.getQuestionNumber()).getQuestion();
            sendText(userId, question);
        }
//        if (result) {
//            sendText(userId, "Всё верно!");
//        }  else {
//            sendText(userId, "Не верно!");
//        }


        //System.out.println(message.getText());
    }
}
