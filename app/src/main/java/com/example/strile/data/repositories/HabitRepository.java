package com.example.strile.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.strile.data.AppDatabase;
import com.example.strile.data.dao_interfaces.HabitDao;
import com.example.strile.data.entities.Habit;

import java.util.List;

import javax.annotation.Nullable;

public class HabitRepository implements Repository<Habit> {
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

    @Nullable
    public LiveData<Habit> getById(long id) {
        return Transformations.map(allHabit, input -> input.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElse(null)
        );
    }

    public void insert(Habit habit) {
        AppDatabase.databaseWriteExecutor.execute(() -> habitDao.insert(habit));
    }

    public void update(Habit habit) {
        AppDatabase.databaseWriteExecutor.execute(() -> habitDao.update(habit));
    }

    public void delete(Habit habit) {
        AppDatabase.databaseWriteExecutor.execute(() -> habitDao.delete(habit));
    }
}
