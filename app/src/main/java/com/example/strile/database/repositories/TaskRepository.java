package com.example.strile.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.strile.database.AppDatabase;
import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

public class TaskRepository implements Repository<Task> {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTask;

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTask = taskDao.getAlphabetizedAll();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -32);
        deleteBeforeDate(calendar.getTime());
    }

    public LiveData<List<Task>> getAll() {
        return allTask;
    }

    @Nullable
    public LiveData<Task> getById(long id) {
        return Transformations.map(allTask, input -> input.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElse(null)
        );
    }

    public void insert(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.insert(task));
    }

    public void update(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.delete(task));
    }

    private void deleteBeforeDate(Date date) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.deleteBeforeDate(date.getTime()));
    }
}