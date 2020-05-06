package com.example.strile.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.Task;

import java.util.List;

public class TaskRepository implements Repository<Task>{
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTask;

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTask = taskDao.getAlphabetizedAll();
    }

    public LiveData<List<Task>> getAll() {
        return allTask;
    }

    public void insert(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    public void update(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.update(task);
        });
    }

    public void delete(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.delete(task);
        });
    }
}