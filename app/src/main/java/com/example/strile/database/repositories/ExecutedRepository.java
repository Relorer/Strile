package com.example.strile.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.dao_interfaces.ExecutedDao;
import com.example.strile.database.entities.Executed;

import java.util.List;

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
