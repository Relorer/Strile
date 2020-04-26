package com.example.strile.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.database.entities.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<TaskModel> getAll();

    @Query("SELECT * FROM task")
    LiveData<List<TaskModel>> getLiveDataAll();

    @Query("SELECT * FROM task WHERE id = :id")
    TaskModel getById(long id);

    @Insert
    void insert(TaskModel taskModel);

    @Update
    void update(TaskModel taskModel);

    @Delete
    void delete(TaskModel taskModel);

}