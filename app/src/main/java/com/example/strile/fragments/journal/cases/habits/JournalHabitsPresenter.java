package com.example.strile.fragments.journal.cases.habits;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.models.CaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.HabitModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalHabitsPresenter extends JournalCasesPresenter {

    private final Repository<Habit> repository;
    private final LiveData<List<Habit>> habits;

    public JournalHabitsPresenter() {
        repository = new HabitRepository(App.getInstance());
        habits = repository.getAll();
    }

    @Override
    public void itemClicked(CaseModel model) {

    }

    @Override
    public void itemStateChanged(CaseModel model) {

    }

    @Override
    public void buttonHidingStateChanged(ButtonHidingModel button) {

    }

    @Override
    protected void updateView() {
        habits.observe(view(), habits -> {
            List<BaseModel> models = new ArrayList<>();
            Date day = new Date();
            for (Habit model : habits) {
                models.add(new HabitModel(false, model.getName(),
                        model.getGoalTime() != 0, model.getStreakByDay(day),
                        model.isCompleteOnDay(day)));
            }
            view().setSortedList(models);
        });
    }
}
