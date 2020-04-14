package com.example.strile.database.models;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.Case;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;

import java.util.List;

public class TaskModel implements CaseModel {
    private final TaskDao dao;

    public TaskModel(final TaskDao dao) {
        this.dao = dao;
    }

    @Override
    public void loadCases(CompleteLoadCallback callback) {
        TaskModel.LoadTasksTask loadTasksTask = new TaskModel.LoadTasksTask(callback, dao);
        loadTasksTask.execute();
    }

    @Override
    public void updateCase(Case c, CompleteCallback callback) {
        TaskModel.UpdateTaskTask updateTaskTask = new TaskModel.UpdateTaskTask(callback, dao);
        updateTaskTask.execute((Task)c);
    }

    @Override
    public void deleteCase(Case c, CompleteCallback callback) {
        TaskModel.DeleteTaskTask deleteTaskTask = new TaskModel.DeleteTaskTask(callback, dao);
        deleteTaskTask.execute((Task)c);
    }

    @Override
    public void insertCase(Case c, CompleteCallback callback) {
        TaskModel.InsertTaskTask insertTaskTask = new TaskModel.InsertTaskTask(callback, dao);
        insertTaskTask.execute((Task)c);
    }

    private static class LoadTasksTask extends AsyncTask<Void, Void, LiveData<List<Task>>> {

        private final TaskDao dao;
        private final CompleteLoadCallback callback;

        LoadTasksTask(CompleteLoadCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Task>> doInBackground(Void... voids) {
            return dao.getLiveDataAll();
        }

        @Override
        protected void onPostExecute(LiveData<List<Task>> tasks) {
            if (callback != null) {
                callback.onComplete(tasks);
            }
        }
    }

    private static class UpdateTaskTask extends AsyncTask<Task, Void, Void> {

        private final TaskDao dao;
        private final CompleteCallback callback;

        UpdateTaskTask(CompleteCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.update(tasks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class DeleteTaskTask extends AsyncTask<Task, Void, Void> {

        private final TaskDao dao;
        private final CompleteCallback callback;

        DeleteTaskTask(CompleteCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.delete(tasks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class InsertTaskTask extends AsyncTask<Task, Void, Void> {

        private final TaskDao dao;
        private final CompleteCallback callback;

        InsertTaskTask(CompleteCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.insert(tasks[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }
}
