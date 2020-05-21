package com.example.strile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.strile.database.dao_interfaces.ExecutedDao;
import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.Executed;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Habit.class, Task.class, Executed.class}, version = 22)
public abstract class AppDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 8;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newScheduledThreadPool(NUMBER_OF_THREADS);
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration() //todo delete
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract HabitDao habitDao();

    public abstract TaskDao taskDao();

    public abstract ExecutedDao executedDao();
}
