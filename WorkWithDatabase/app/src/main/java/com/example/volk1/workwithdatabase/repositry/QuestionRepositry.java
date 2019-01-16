package com.example.volk1.workwithdatabase.repositry;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.volk1.workwithdatabase.roomDB.QuestionRoomDatabase;
import com.example.volk1.workwithdatabase.roomDB.dao.QuestionDao;
import com.example.volk1.workwithdatabase.roomDB.entity.Question;

import java.util.List;

public class QuestionRepositry {

    private QuestionDao mQuestionDao;
    private LiveData<List<Question>> mAllQuestion;

    public QuestionRepositry(Application application) {
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(application);
        mQuestionDao = db.questionDao();
        mAllQuestion = mQuestionDao.getAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestion() {
        return mAllQuestion;
    }

    public void insert(Question question) {
        new InsertAsyncTask(mQuestionDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao mQuestionDao;

        public InsertAsyncTask(QuestionDao questionDao) {
            this.mQuestionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            mQuestionDao.insert(questions[0]);
            return null;
        }
    }
}
