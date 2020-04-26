package com.example.strile.database.models;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.strile.database.dao_interfaces.TaskDao;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;

import java.util.List;

public class TaskDatabaseModel implements CaseDatabaseModel {
    private final TaskDao dao;

    public TaskDatabaseModel(final TaskDao dao) {
        this.dao = dao;
    }

    @Override
    public void loadCases(CompleteLoadCallback callback) {
        TaskDatabaseModel.LoadTasksTask loadTasksTask = new TaskDatabaseModel.LoadTasksTask(callback, dao);
        loadTasksTask.execute();
    }

    @Override
    public void updateCase(CaseModel c, CompleteCallback callback) {
        TaskDatabaseModel.UpdateTaskTask updateTaskTask = new TaskDatabaseModel.UpdateTaskTask(callback, dao);
        updateTaskTask.execute((com.example.strile.database.entities.TaskModel)c);
    }

    @Override
    public void deleteCase(CaseModel c, CompleteCallback callback) {
        TaskDatabaseModel.DeleteTaskTask deleteTaskTask = new TaskDatabaseModel.DeleteTaskTask(callback, dao);
        deleteTaskTask.execute((com.example.strile.database.entities.TaskModel)c);
    }

    @Override
    public void insertCase(CaseModel c, CompleteCallback callback) {
        TaskDatabaseModel.InsertTaskTask insertTaskTask = new TaskDatabaseModel.InsertTaskTask(callback, dao);
        insertTaskTask.execute((com.example.strile.database.entities.TaskModel)c);
    }

    private static class LoadTasksTask extends AsyncTask<Void, Void, LiveData<List<com.example.strile.database.entities.TaskModel>>> {

        private final TaskDao dao;
        private final CompleteLoadCallback callback;

        LoadTasksTask(CompleteLoadCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected LiveData<List<com.example.strile.database.entities.TaskModel>> doInBackground(Void... voids) {
            return dao.getLiveDataAll();
        }

        @Override
        protected void onPostExecute(LiveData<List<com.example.strile.database.entities.TaskModel>> tasks) {
            if (callback != null) {
                callback.onComplete(tasks);
            }
        }
    }

    private static class UpdateTaskTask extends AsyncTask<com.example.strile.database.entities.TaskModel, Void, Void> {

        private final TaskDao dao;
        private final CompleteCallback callback;

        UpdateTaskTask(CompleteCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.example.strile.database.entities.TaskModel... taskModels) {
            dao.update(taskModels[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class DeleteTaskTask extends AsyncTask<com.example.strile.database.entities.TaskModel, Void, Void> {

        private final TaskDao dao;
        private final CompleteCallback callback;

        DeleteTaskTask(CompleteCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.example.strile.database.entities.TaskModel... taskModels) {
            dao.delete(taskModels[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class InsertTaskTask extends AsyncTask<com.example.strile.database.entities.TaskModel, Void, Void> {

        private final TaskDao dao;
        private final CompleteCallback callback;

        InsertTaskTask(CompleteCallback callback, TaskDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.example.strile.database.entities.TaskModel... taskModels) {
            dao.insert(taskModels[0]);
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
