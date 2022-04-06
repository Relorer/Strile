package com.example.strile.data.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.strile.data.entities.Executed;

import java.util.List;

@Dao
public interface ExecutedDao {
    @Query("SELECT * FROM executed ORDER BY dateComplete DESC")
    LiveData<List<Executed>> getAll();

    @Query("SELECT * FROM executed WHERE dateComplete >= :date")
    LiveData<List<Executed>> getLiveDataLaterDate(long date);

    @Query("DELETE FROM executed WHERE dateComplete < :date")
    void deleteBeforeDate(long date);

    @Query("DELETE FROM executed WHERE dateComplete = (SELECT MAX(dateComplete) FROM executed WHERE caseId == :id AND typeCase == :type)")
    void deleteByCaseId(long id, String type);

    @Insert
    void insert(Executed executed);

    @Update
    void update(Executed executed);

    @Delete
    void delete(Executed executed);
}
