package org.example;

public class UserData {
    private int questionNumber;
    private int score;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UserData() {
        this.questionNumber = 0;
        this.score = 0;
    }
}
