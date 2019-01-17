package com.example.volk1.workwithdatabase.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.volk1.workwithdatabase.repositry.QuestionRepositry;
import com.example.volk1.workwithdatabase.roomDB.entity.Question;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepositry mQuestionRepositry;
    private LiveData<List<Question>> mAllQuestion;

    public QuestionViewModel(@NonNull Application application) {
        super(application);

        mQuestionRepositry = new QuestionRepositry(application);
        mAllQuestion = mQuestionRepositry.getAllQuestion();
    }

    public LiveData<List<Question>> getAllQuestion() {
        return mAllQuestion;
    }

    public void insert(Question question) {
        mQuestionRepositry.insert(question);
    }

    public void deleteAll() {
        mQuestionRepositry.deleteAll();
    }

    public void deleteQuestion(Question question) {
        mQuestionRepositry.deleteSingleQuestion(question);
    }

}
