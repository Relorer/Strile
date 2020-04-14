package com.example.strile.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.database.entities.Habit;

import java.util.List;

@Dao
public interface HabitDao {

    @Query("SELECT * FROM habit")
    List<Habit> getAll();

    @Query("SELECT * FROM habit")
    LiveData<List<Habit>> getLiveDataAll();

    @Query("SELECT * FROM habit WHERE id = :id")
    Habit getById(long id);

    @Insert
    void insert(Habit habit);

    @Update
    void update(Habit habit);

    @Delete
    void delete(Habit habit);

}