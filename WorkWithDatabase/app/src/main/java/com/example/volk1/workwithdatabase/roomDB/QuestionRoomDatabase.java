package com.example.volk1.workwithdatabase.roomDB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.volk1.workwithdatabase.roomDB.dao.QuestionDao;
import com.example.volk1.workwithdatabase.roomDB.entity.Question;
import com.example.volk1.workwithdatabase.roomDB.random_id.UniqueID;

@Database(entities = {Question.class}, version = 2, exportSchema = false)
public abstract class QuestionRoomDatabase extends RoomDatabase {

    public abstract QuestionDao questionDao();

    private static QuestionRoomDatabase INSTANCE;

    public static QuestionRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuestionRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            QuestionRoomDatabase.class,
                            "question_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final QuestionDao mQuestionDao;

        String[] title = {"Title One", "Title Two", "Title Three"};
        String[] questions = {"Question One", "Question Two", "Question Three"};
        String[] answer = {"Answer One", "Answer Two", "Answer Three"};

        PopulateDbAsync(QuestionRoomDatabase instance) {
            mQuestionDao = instance.questionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // If we have no words, then create the initial list of words
            if (mQuestionDao.getSingleQuestion().length < 1) {
                for (int i = 0; i <= title.length - 1; i++) {
                    int id = UniqueID.getID();
                    Question question = new Question(id, title[i], questions[i], answer[i]);
                    mQuestionDao.insert(question);
                }
            }
            return null;
        }
    }
}
