package com.example.strile.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.database.entities.HabitModel;

import java.util.List;

@Dao
public interface HabitDao {

    @Query("SELECT * FROM habit")
    List<HabitModel> getAll();

    @Query("SELECT * FROM habit")
    LiveData<List<HabitModel>> getLiveDataAll();

    @Query("SELECT * FROM habit WHERE id = :id")
    HabitModel getById(long id);

    @Query("SELECT COUNT(*) FROM habit")
    int getCount();

    @Insert
    void insert(HabitModel habitModel);

    @Update
    void update(HabitModel habitModel);

    @Delete
    void delete(HabitModel habitModel);

}