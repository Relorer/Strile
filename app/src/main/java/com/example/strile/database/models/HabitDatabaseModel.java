package com.example.strile.database.models;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteWithReturnCallback;

import java.util.List;

public class HabitDatabaseModel implements CaseDatabaseModel {
    private final HabitDao dao;

    public HabitDatabaseModel(final HabitDao dao) {
        this.dao = dao;
    }

    @Override
    public void loadCases(CompleteLoadCallback callback) {
        LoadHabitsTask loadHabitsTask = new LoadHabitsTask(callback, dao);
        loadHabitsTask.execute();
    }

    public void getCount(CompleteWithReturnCallback<Integer> callback) {
        LoadCountHabitsTask loadHabitsTask = new LoadCountHabitsTask(callback, dao);
        loadHabitsTask.execute();
    }

    @Override
    public void updateCase(CaseModel c, CompleteCallback callback) {
        UpdateHabitTask updateHabitTask = new UpdateHabitTask(callback, dao);
        updateHabitTask.execute((com.example.strile.database.entities.HabitModel)c);
    }

    @Override
    public void deleteCase(CaseModel c, CompleteCallback callback) {
        DeleteHabitTask deleteHabitTask = new DeleteHabitTask(callback, dao);
        deleteHabitTask.execute((com.example.strile.database.entities.HabitModel)c);
    }

    @Override
    public void insertCase(CaseModel c, CompleteCallback callback) {
        InsertHabitTask insertHabitTask = new InsertHabitTask(callback, dao);
        insertHabitTask.execute((com.example.strile.database.entities.HabitModel)c);
    }

    private static class LoadHabitsTask extends AsyncTask<Void, Void, LiveData<List<com.example.strile.database.entities.HabitModel>>> {

        private final HabitDao dao;
        private final CompleteLoadCallback callback;

        LoadHabitsTask(CompleteLoadCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected LiveData<List<com.example.strile.database.entities.HabitModel>> doInBackground(Void... voids) {
            return dao.getLiveDataAll();
        }

        @Override
        protected void onPostExecute(LiveData<List<com.example.strile.database.entities.HabitModel>> habits) {
            if (callback != null) {
                callback.onComplete(habits);
            }
        }
    }

    private static class LoadCountHabitsTask extends AsyncTask<Void, Void, Integer> {

        private final HabitDao dao;
        private final CompleteWithReturnCallback<Integer> callback;

        LoadCountHabitsTask(CompleteWithReturnCallback<Integer> callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return dao.getCount();
        }

        @Override
        protected void onPostExecute(Integer count) {
            if (callback != null) {
                callback.onComplete(count);
            }
        }
    }

    private static class UpdateHabitTask extends AsyncTask<com.example.strile.database.entities.HabitModel, Void, Void> {

        private final HabitDao dao;
        private final CompleteCallback callback;

        UpdateHabitTask(CompleteCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.example.strile.database.entities.HabitModel... habitModels) {
            dao.update(habitModels[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class DeleteHabitTask extends AsyncTask<com.example.strile.database.entities.HabitModel, Void, Void> {

        private final HabitDao dao;
        private final CompleteCallback callback;

        DeleteHabitTask(CompleteCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.example.strile.database.entities.HabitModel... habitModels) {
            dao.delete(habitModels[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class InsertHabitTask extends AsyncTask<com.example.strile.database.entities.HabitModel, Void, Void> {

        private final HabitDao dao;
        private final CompleteCallback callback;

        InsertHabitTask(CompleteCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(com.example.strile.database.entities.HabitModel... habitModels) {
            dao.insert(habitModels[0]);
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