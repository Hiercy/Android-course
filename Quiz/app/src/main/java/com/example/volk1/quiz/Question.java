package com.example.volk1.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question {

    private int textResID;
    private boolean isAnswerCorrect;

    public Question(int textResID, boolean isAnswerCorrect) {
        this.textResID = textResID;
        this.isAnswerCorrect = isAnswerCorrect;
    }

    public int getTextResID() {
        return textResID;
    }

    public void setTextResID(int textResID) {
        this.textResID = textResID;
    }

    public boolean IsAnswerCorrect() {
        return isAnswerCorrect;
    }

    public void setAnswerCorrect(boolean isAnswerCorrect) { this.isAnswerCorrect = isAnswerCorrect; }
}
