package com.example.strile.database.models;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.strile.database.dao_interfaces.HabitDao;
import com.example.strile.database.entities.Case;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;
import java.util.List;

public class HabitModel implements CaseModel {
    private final HabitDao dao;

    public HabitModel(final HabitDao dao) {
        this.dao = dao;
    }

    @Override
    public void loadCases(CompleteLoadCallback callback) {
        LoadHabitsTask loadHabitsTask = new LoadHabitsTask(callback, dao);
        loadHabitsTask.execute();
    }

    @Override
    public void updateCase(Case c, CompleteCallback callback) {
        UpdateHabitTask updateHabitTask = new UpdateHabitTask(callback, dao);
        updateHabitTask.execute((Habit)c);
    }

    @Override
    public void deleteCase(Case c, CompleteCallback callback) {
        DeleteHabitTask deleteHabitTask = new DeleteHabitTask(callback, dao);
        deleteHabitTask.execute((Habit)c);
    }

    @Override
    public void insertCase(Case c, CompleteCallback callback) {
        InsertHabitTask insertHabitTask = new InsertHabitTask(callback, dao);
        insertHabitTask.execute((Habit)c);
    }

    private static class LoadHabitsTask extends AsyncTask<Void, Void, LiveData<List<Habit>>> {

        private final HabitDao dao;
        private final CompleteLoadCallback callback;

        LoadHabitsTask(CompleteLoadCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Habit>> doInBackground(Void... voids) {
            return dao.getLiveDataAll();
        }

        @Override
        protected void onPostExecute(LiveData<List<Habit>> habits) {
            if (callback != null) {
                callback.onComplete(habits);
            }
        }
    }

    private static class UpdateHabitTask extends AsyncTask<Habit, Void, Void> {

        private final HabitDao dao;
        private final CompleteCallback callback;

        UpdateHabitTask(CompleteCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            dao.update(habits[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class DeleteHabitTask extends AsyncTask<Habit, Void, Void> {

        private final HabitDao dao;
        private final CompleteCallback callback;

        DeleteHabitTask(CompleteCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            dao.delete(habits[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class InsertHabitTask extends AsyncTask<Habit, Void, Void> {

        private final HabitDao dao;
        private final CompleteCallback callback;

        InsertHabitTask(CompleteCallback callback, HabitDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            dao.insert(habits[0]);
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