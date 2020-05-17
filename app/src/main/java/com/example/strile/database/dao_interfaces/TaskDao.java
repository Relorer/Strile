package com.example.strile.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.database.entities.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task ORDER BY name ASC")
    LiveData<List<Task>> getAlphabetizedAll();

    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<Task> getById(long id);

    @Query("DELETE FROM executed WHERE dateComplete < :date")
    void deleteBeforeDate(long date);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}