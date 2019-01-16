package com.example.volk1.workwithdatabase.roomDB.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.volk1.workwithdatabase.roomDB.entity.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Question question);

    @Query("DELETE FROM question_list")
    void deleteAll();

    @Query("SELECT * FROM question_list ORDER BY question, title ASC")
    LiveData<List<Question>> getAllQuestions();

}
