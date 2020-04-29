package com.example.strile.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.database.entities.TaskModel;

@Database(entities = {HabitModel.class, TaskModel.class}, version = 51)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HabitDao habitDao();
    public abstract TaskDao taskDao();
}
