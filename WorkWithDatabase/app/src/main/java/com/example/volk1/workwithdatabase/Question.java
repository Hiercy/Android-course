package com.example.volk1.workwithdatabase;

public class Question {

    private String title;
    private String question;

    public Question(String title, String question) {
        this.title = title;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getTitle() {
        return title;
    }
}