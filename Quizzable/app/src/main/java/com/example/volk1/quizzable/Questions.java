package com.example.volk1.quizzable;

public class Questions {

    private int question;
    private boolean answerIsTrueOrNot;

    public Questions(int question, boolean answerIsTrueOrNot) {
        this.question = question;
        this.answerIsTrueOrNot = answerIsTrueOrNot;
    }

    public boolean isAnswerIsTrueOrNot() {
        return answerIsTrueOrNot;
    }

    public void setAnswerIsTrueOrNot(boolean answerIsTrueOrNot) {
        this.answerIsTrueOrNot = answerIsTrueOrNot;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
}
