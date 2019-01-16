package com.example.volk1.workwithdatabase.roomDB.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "question_list")
public class Question {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "question_id")
    private int ID;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "question")
    private String question;

    public Question(@NonNull  int ID, @NonNull String title, @NonNull String question) {
        this.ID = ID;
        this.title = title;
        this.question = question;
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
}