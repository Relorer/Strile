package com.example.strile.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getLiveDataAll();

    @Query("SELECT * FROM task WHERE id = :id")
    Task getById(long id);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

}