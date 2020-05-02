package com.example.strile.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.database.entities.CompleteCaseModel;
import com.example.strile.database.entities.HabitModel;

import java.util.List;

@Dao
public interface CompleteCaseDao {

    @Query("SELECT * FROM complete_case")
    List<CompleteCaseModel> getAll();

    @Query("SELECT * FROM complete_case")
    LiveData<List<CompleteCaseModel>> getLiveDataAll();

    @Query("SELECT * FROM complete_case WHERE dateComplete >= :date")
    LiveData<List<CompleteCaseModel>> getLiveDataLaterDate(long date);

    @Query("SELECT * FROM complete_case ORDER BY id DESC LIMIT 7")
    LiveData<List<CompleteCaseModel>> getLiveDataLastSeven();

    @Query("SELECT * FROM complete_case WHERE id = :id")
    CompleteCaseModel getById(long id);

    @Insert
    void insert(CompleteCaseModel completeCaseModel);

    @Update
    void update(CompleteCaseModel completeCaseModel);

    @Delete
    void delete(CompleteCaseModel completeCaseModel);

}
