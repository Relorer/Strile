package com.example.strile.database.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.database.entities.Executed;

import java.util.List;

@Dao
public interface ExecutedDao {
    @Query("SELECT * FROM executed ORDER BY dateComplete DESC")
    LiveData<List<Executed>> getAll();

    @Query("SELECT * FROM Executed WHERE dateComplete >= :date")
    LiveData<List<Executed>> getLiveDataLaterDate(long date);

    @Insert
    void insert(Executed executed);

    @Update
    void update(Executed executed);

    @Delete
    void delete(Executed executed);
}
