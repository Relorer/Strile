package com.example.strile.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;

@Database(entities = {Habit.class, Task.class}, version = 10)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HabitDao habitDao();
    public abstract TaskDao taskDao();
}
