package com.example.strile.database.models;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.strile.database.dao_interfaces.CompleteCaseDao;
import com.example.strile.database.entities.CompleteCaseModel;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;

import java.util.List;

public class CompleteCaseDatabaseModel {
    private final CompleteCaseDao dao;

    public CompleteCaseDatabaseModel(final CompleteCaseDao dao) {
        this.dao = dao;
    }

    public void loadCompleteCases(CompleteLoadCallback callback) {
        LoadCompleteModelsTask loadCompleteModelsTask = new LoadCompleteModelsTask(callback, dao);
        loadCompleteModelsTask.execute();
    }

    public void loadCompleteCasesLastSeven(CompleteLoadCallback callback) {
        LoadCompleteModelsLastSevenTask loadCompleteModelsTask = new LoadCompleteModelsLastSevenTask(callback, dao);
        loadCompleteModelsTask.execute();
    }

    public void loadCompleteCasesLaterDate(long date, CompleteLoadCallback callback) {
        LoadCompleteModelsLaterDateTask loadCompleteModelsTask = new LoadCompleteModelsLaterDateTask(callback, dao);
        loadCompleteModelsTask.execute(date);
    }

    public void updateCompleteCase(CompleteCaseModel c, CompleteCallback callback) {
        UpdateCompleteModelTask updateCompleteModelTask = new UpdateCompleteModelTask(callback, dao);
        updateCompleteModelTask.execute((CompleteCaseModel)c);
    }

    public void deleteCompleteCase(CompleteCaseModel c, CompleteCallback callback) {
        DeleteCompleteModelTask deleteCompleteModelTask = new DeleteCompleteModelTask(callback, dao);
        deleteCompleteModelTask.execute((CompleteCaseModel)c);
    }

    public void insertCompleteCase(CompleteCaseModel c, CompleteCallback callback) {
        InsertCompleteModelTask insertCompleteModelTask = new InsertCompleteModelTask(callback, dao);
        insertCompleteModelTask.execute((CompleteCaseModel)c);
    }

    private static class LoadCompleteModelsTask extends AsyncTask<Void, Void, LiveData<List<CompleteCaseModel>>> {

        private final CompleteCaseDao dao;
        private final CompleteLoadCallback callback;

        LoadCompleteModelsTask(CompleteLoadCallback callback, CompleteCaseDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        protected LiveData<List<CompleteCaseModel>> doInBackground(Void... voids) {
            return dao.getLiveDataAll();
        }

        protected void onPostExecute(LiveData<List<CompleteCaseModel>> CompleteModels) {
            if (callback != null) {
                callback.onComplete(CompleteModels);
            }
        }
    }

    private static class LoadCompleteModelsLastSevenTask extends AsyncTask<Void, Void, LiveData<List<CompleteCaseModel>>> {

        private final CompleteCaseDao dao;
        private final CompleteLoadCallback callback;

        LoadCompleteModelsLastSevenTask(CompleteLoadCallback callback, CompleteCaseDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        protected LiveData<List<CompleteCaseModel>> doInBackground(Void... voids) {
            return dao.getLiveDataLastSeven();
        }

        protected void onPostExecute(LiveData<List<CompleteCaseModel>> CompleteModels) {
            if (callback != null) {
                callback.onComplete(CompleteModels);
            }
        }
    }

    private static class LoadCompleteModelsLaterDateTask extends AsyncTask<Long, Void, LiveData<List<CompleteCaseModel>>> {

        private final CompleteCaseDao dao;
        private final CompleteLoadCallback callback;

        LoadCompleteModelsLaterDateTask(CompleteLoadCallback callback, CompleteCaseDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        protected LiveData<List<CompleteCaseModel>> doInBackground(Long... longs) {
            return dao.getLiveDataLaterDate(longs[0]);
        }

        protected void onPostExecute(LiveData<List<CompleteCaseModel>> CompleteModels) {
            if (callback != null) {
                callback.onComplete(CompleteModels);
            }
        }
    }

    private static class UpdateCompleteModelTask extends AsyncTask<CompleteCaseModel, Void, Void> {

        private final CompleteCaseDao dao;
        private final CompleteCallback callback;

        UpdateCompleteModelTask(CompleteCallback callback, CompleteCaseDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        protected Void doInBackground(CompleteCaseModel... completeModels) {
            dao.update(completeModels[0]);
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class DeleteCompleteModelTask extends AsyncTask<CompleteCaseModel, Void, Void> {

        private final CompleteCaseDao dao;
        private final CompleteCallback callback;

        DeleteCompleteModelTask(CompleteCallback callback, CompleteCaseDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        protected Void doInBackground(CompleteCaseModel... completeModels) {
            dao.delete(completeModels[0]);
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    private static class InsertCompleteModelTask extends AsyncTask<CompleteCaseModel, Void, Void> {

        private final CompleteCaseDao dao;
        private final CompleteCallback callback;

        InsertCompleteModelTask(CompleteCallback callback, CompleteCaseDao dao) {
            this.callback = callback;
            this.dao = dao;
        }

        protected Void doInBackground(CompleteCaseModel... completeModels) {
            dao.insert(completeModels[0]);
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                callback.onComplete();
            }
        }
    }
}
