package com.example.strile;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.models.CompleteCaseDatabaseModel;
import com.example.strile.database.models.HabitDatabaseModel;
import com.example.strile.database.models.TaskDatabaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public class App extends Application {
    final String APP_PREFERENCES = "app_preferences";
    final String PERSON_NAME = "person_name";
    final String EXPERIENCE = "experience";
    final String REMAINED = "remained";
    final String DAYS = "days";
    final String LEVEL = "level";

    private static App instance;
    private HabitDatabaseModel habitModel;
    private TaskDatabaseModel taskModel;
    private CompleteCaseDatabaseModel completeCaseModel;

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        habitModel = new HabitDatabaseModel(database.habitDao());
        taskModel = new TaskDatabaseModel(database.taskDao());
        completeCaseModel = new CompleteCaseDatabaseModel(database.completeCaseDao());

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        sharedPreferences.edit().putString(PERSON_NAME, "Yuri Trofimov").apply();

        BaseModel.setPreferences(sharedPreferences);
        BaseModel.setMaxId(sharedPreferences.getInt(BaseModel.PREFERENCES_KEY_MAX_ID, 0));
    }

    public HabitDatabaseModel getHabitModel() {
        return habitModel;
    }

    public TaskDatabaseModel getTaskModel() {
        return taskModel;
    }

    public CompleteCaseDatabaseModel getCompleteCaseModel() {
        return completeCaseModel;
    }

    public static App getInstance() {
        return instance;
    }

    public void addExperience(long exp) {
        long level = sharedPreferences.getLong(LEVEL, 1);
        long experience = sharedPreferences.getLong(EXPERIENCE, 0);
        long remained = sharedPreferences.getLong(REMAINED, 100);

        experience += exp;
        experience = Math.max(0, experience);
        if (experience >= remained) {
            ++level;
            experience -= remained;
            remained *= 1.3f;
        }

        sharedPreferences.edit()
                .putLong(LEVEL, level)
                .putLong(EXPERIENCE, experience)
                .putLong(REMAINED, remained)
                .apply();

    }

    public String getPersonName() {
        return sharedPreferences.getString(PERSON_NAME, "No user name");
    }

    public long getLevel() {
        return sharedPreferences.getLong(LEVEL, 1);
    }

    public long getExperience() {
        return sharedPreferences.getLong(EXPERIENCE, 0);
    }

    public long getDays() {
        return sharedPreferences.getLong(DAYS, 0);
    }

    public long getRemained() {
        return sharedPreferences.getLong(REMAINED, 100);
    }
}