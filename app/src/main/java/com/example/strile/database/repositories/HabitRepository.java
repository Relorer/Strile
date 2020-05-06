package com.example.strile.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.dao_interfaces.ExecutedDao;
import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.entities.Executed;
import com.example.strile.database.entities.Habit;

import java.util.List;

public class HabitRepository implements Repository<Habit>{
    private final HabitDao habitDao;
    private final LiveData<List<Habit>> allHabit;

    public HabitRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        habitDao = db.habitDao();
        allHabit = habitDao.getAlphabetizedAll();
    }

    public LiveData<List<Habit>> getAll() {
        return allHabit;
    }

    public void insert(Habit habit) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            habitDao.insert(habit);
        });
    }

    public void update(Habit habit) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            habitDao.update(habit);
        });
    }

    public void delete(Habit habit) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            habitDao.delete(habit);
        });
    }
}
