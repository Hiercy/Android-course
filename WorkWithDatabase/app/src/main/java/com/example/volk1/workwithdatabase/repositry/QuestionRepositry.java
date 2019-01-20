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
        new InsertAsyncTask(mQuestionDao).execute(question);
    }

    public void deleteAll() {
        new deleteAllQuestionAsyncTask(mQuestionDao).execute();
    }

    public void deleteSingleQuestion(Question question) {
        new deleteSingleQuestionAsyncTask(mQuestionDao).execute(question);
    }

    public void update(Question questions) {
        new updateSingleQuestionAsyncTask(mQuestionDao).execute(questions);
    }

    private static class InsertAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao mQuestionDao;

        InsertAsyncTask(QuestionDao questionDao) {
            this.mQuestionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            mQuestionDao.insert(questions[0]);
            return null;
        }
    }

    private static class deleteAllQuestionAsyncTask extends AsyncTask<Void, Void, Void> {

        private QuestionDao mQuestionDao;

        deleteAllQuestionAsyncTask(QuestionDao questionDao) {
            mQuestionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mQuestionDao.deleteAll();
            return null;
        }
    }

    private static class deleteSingleQuestionAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao mQuestionDao;

        deleteSingleQuestionAsyncTask(QuestionDao questionDao) {
            mQuestionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            mQuestionDao.deleteQuestion(questions[0]);
            return null;
        }
    }

    private static class updateSingleQuestionAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao mQuestionDao;

        updateSingleQuestionAsyncTask(QuestionDao questionDao) {
            mQuestionDao = questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            mQuestionDao.update(questions[0]);
            return null;
        }
    }
}
