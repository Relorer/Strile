package com.example.strile;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.models.HabitModel;
import com.example.strile.database.models.TaskModel;
import com.example.strile.sevice.recycler_view_adapter.ItemModel;

public class App extends Application {
    final String APP_PREFERENCES = "app_preferences";

    private static App instance;
    private HabitModel habitModel;
    private TaskModel taskModel;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        habitModel = new HabitModel(database.habitDao());
        taskModel = new TaskModel(database.taskDao());

        SharedPreferences sp = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        ItemModel.setPreferences(sp);
        ItemModel.setMaxId(sp.getInt(ItemModel.PREFERENCES_KEY_MAX_ID, 0));
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
