package org.example.question;

public abstract class AbstractQuestion {
    private String qustion;

    public AbstractQuestion(String qustion) {
        this.qustion = qustion;
    }

    public String getQuestion() {
        return qustion;
    }

    public abstract boolean checkAnswer(String answer);
}
