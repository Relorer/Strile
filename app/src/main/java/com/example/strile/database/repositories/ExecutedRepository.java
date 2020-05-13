package com.example.strile.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.dao_interfaces.ExecutedDao;
import com.example.strile.database.entities.Executed;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;

import java.util.List;

import javax.annotation.Nullable;

public class ExecutedRepository implements Repository<Executed> {
    private final ExecutedDao executedDao;
    private final LiveData<List<Executed>> allExecuted;

    public ExecutedRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        executedDao = db.executedDao();
        allExecuted = executedDao.getAlphabetizedAll();
    }

    public LiveData<List<Executed>> getAll() {
        return allExecuted;
    }

    @Nullable
    public LiveData<Executed> getById(long id) {
        return Transformations.map(allExecuted, input -> input.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElse(null)
        );
    }

    public void insert(Executed executed) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            executedDao.insert(executed);
        });
    }

    public void update(Executed executed) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            executedDao.update(executed);
        });
    }

    public void delete(Executed executed) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            executedDao.delete(executed);
        });
    }
}
