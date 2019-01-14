package com.example.volk1.roomwordssample.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.volk1.roomwordssample.entity.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    // @Delete
    @Query("DELETE FROM word_list")
    void deleteAll();

    /*
        LiveData, which is a lifecycle library class for data observation,
        can help your app respond to data changes.
        If you use a return value of type LiveData in your method description,
        Room generates all necessary code to update the LiveData when the database is updated.
     */
    @Query("SELECT * FROM word_list ORDER BY word ASC")
    LiveData<List<Word>> getAllWord();
}
