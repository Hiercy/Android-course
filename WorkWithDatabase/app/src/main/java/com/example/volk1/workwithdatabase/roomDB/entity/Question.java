package com.example.volk1.workwithdatabase.roomDB.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "question_list")
public class Question {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    private int ID;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "question")
    private String question;

    @NonNull
    @ColumnInfo(name = "answer")
    private String answer;

    public Question( int ID, @NonNull String title, @NonNull String question, @NonNull String answer) {
        this.ID = ID;
        this.title = title;
        this.question = question;
        this.answer = answer;
    }

    public int getID() {
        return  ID;
    }

    public String getQuestion() {
        return question;
    }

    public String getTitle() {
        return title;
    }

    public String getAnswer() {
        return answer;
    }
}