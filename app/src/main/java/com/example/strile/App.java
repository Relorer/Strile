package com.example.strile;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.room.Room;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.entities.Task;
import com.example.strile.database.models.HabitModel;
import com.example.strile.database.models.TaskModel;

import java.util.Calendar;
import java.util.Locale;

public class App extends Application {
    private static App instance;
    private HabitModel habitModel;
    private TaskModel taskModel;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        database.clearAllTables();

        habitModel = new HabitModel(database.habitDao());
        taskModel = new TaskModel(database.taskDao());
    }

    public HabitModel getHabitModel() {
        return habitModel;
    }

    public TaskModel getTaskModel() {
        return taskModel;
    }

    public static App getInstance() {
        return instance;
    }

}
