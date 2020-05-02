package com.example.strile.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.strile.database.dao_interfaces.CompleteCaseDao;
import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.CompleteCaseModel;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.database.entities.TaskModel;

@Database(entities = {HabitModel.class, TaskModel.class, CompleteCaseModel.class}, version = 55)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HabitDao habitDao();
    public abstract TaskDao taskDao();
    public abstract CompleteCaseDao completeCaseDao();
}
