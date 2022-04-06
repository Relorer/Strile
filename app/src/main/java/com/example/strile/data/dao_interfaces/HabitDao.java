package com.example.strile.data.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.data.entities.Habit;

import java.util.List;

@Dao
public interface HabitDao {
    @Query("SELECT * FROM habit ORDER BY name ASC")
    LiveData<List<Habit>> getAlphabetizedAll();

    @Query("SELECT * FROM habit WHERE id = :id")
    LiveData<Habit> getById(long id);

    @Query("SELECT COUNT(*) FROM habit")
    int getCount();

    @Insert
    void insert(Habit habit);

    @Update
    void update(Habit habit);

    @Delete
    void delete(Habit habit);
}