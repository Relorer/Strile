package com.example.strile.fragments.progress;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.database.models.CompleteCaseDatabaseModel;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.database.entities.CompleteCaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.GraphProgressModel;
import com.example.strile.sevice.recycler_view_adapter.models.InfoProgressModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProgressPresenter extends BasePresenter<CompleteCaseDatabaseModel, ProgressFragment> {

    private LiveData<List<CompleteCaseModel>> completeCases;
    private LiveData<List<CompleteCaseModel>> completeCasesLaterDate;


    private InfoProgressModel infoProgress = new InfoProgressModel();
    private GraphProgressModel graphProgress = new GraphProgressModel();
    private List<CompleteCaseModel> completeCaseModels;
    private List<CompleteCaseModel> completeCaseModelsLaterDate;

    public ProgressPresenter() {
        model = App.getInstance().getCompleteCaseModel();
        model.loadCompleteCasesLastSeven(list -> {
            completeCases = (LiveData<List<CompleteCaseModel>>) list;
            updateView();
        });

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -6);

        model.loadCompleteCasesLaterDate(DateManager.getDateWithoutTime(calendar.getTimeInMillis()).getTime(), list -> {
            completeCasesLaterDate = (LiveData<List<CompleteCaseModel>>) list;
            updateView();
        });


        graphProgress.setTopMargin(true);


    }

    @Override
    protected void updateView() {
        if (completeCases != null) {
            completeCases.removeObservers(view());
            completeCases.observe(view(), caseModels -> {
                completeCaseModels = caseModels;
                updateSortedListOnScreen();
            });
        }
        if (completeCasesLaterDate != null) {
            completeCasesLaterDate.removeObservers(view());
            completeCasesLaterDate.observe(view(), caseModels -> {
                completeCaseModelsLaterDate = caseModels;
                updateGraph();
            });
        }
    }

    private void updateSortedListOnScreen() {
        if (setupDone()) {
            List<BaseModel> list = new ArrayList<>();
            list.add(infoProgress);
            list.add(graphProgress);
            if (completeCaseModels.size() > 0) {
                completeCaseModels.sort((o1, o2) -> (int) (o2.getDateComplete() - o1.getDateComplete()));
                completeCaseModels.get(0).setTopMargin(true);
                list.addAll(completeCaseModels);
            }
            view().setSortedList(list);
        }
    }

    private void updateGraph() {
        App.getInstance().getHabitModel().getCount(count -> {
            graphProgress.setMaxHabit(100);
            graphProgress.setMaxTask(0);
            final int size = 14;
            int[] habitsByDay = new int[size];
            int[] tasksByDay = new int[size];

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -6);
            for (int i = 0; i < size; i++) {
                if (i % 2 == 0) {
                    habitsByDay[i] = calendar.get(Calendar.DATE);
                    tasksByDay[i] = calendar.get(Calendar.DATE);
                } else {
                    long currentDate = DateManager.getDateWithoutTime(calendar.getTimeInMillis()).getTime();
                    float countHabits = 0;
                    int countTasks = 0;
                    for (CompleteCaseModel model : completeCaseModelsLaterDate) {
                        if (model.getDateComplete() == currentDate) {
                            if (model.getTypeCase().equals(HabitModel.class.getCanonicalName())) {
                                countHabits += model.getExperience() > 0 ? 1 : -1;
                            } else {
                                countTasks += model.getExperience() > 0 ? 1 : -1;
                            }
                        }
                    }

                    if (count > 0)
                        habitsByDay[i] = (int) (countHabits / count * 100);
                    tasksByDay[i] = countTasks;
                    if (graphProgress.getMaxTask() < countTasks) graphProgress.setMaxTask(countTasks);

                    calendar.add(Calendar.DATE, 1);
                }
            }

            graphProgress.setHabitsByDays(habitsByDay);
            graphProgress.setTasksByDays(tasksByDay);
            setInfoProgress();
        });
    }

    private void setInfoProgress() {
        infoProgress.setDays(App.getInstance().getDays());
        infoProgress.setExperience(App.getInstance().getExperience());
        infoProgress.setLevel(App.getInstance().getLevel());
        infoProgress.setName(App.getInstance().getPersonName());
        infoProgress.setRemained(App.getInstance().getRemained());
    }
}