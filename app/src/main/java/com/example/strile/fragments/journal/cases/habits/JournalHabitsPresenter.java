package com.example.strile.fragments.journal.cases.habits;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.example.strile.App;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;
import com.example.strile.sevice.Day;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.models.CaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.HabitModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

public class JournalHabitsPresenter extends JournalCasesPresenter<JournalHabitsFragment> {

    private final Repository<Habit> repository;
    private final LiveData<List<HabitModel>> habits;

    private boolean showCompleted = false;
    private final int idButton;

    public JournalHabitsPresenter() {
        repository = new HabitRepository(App.getInstance());
        final LiveData<List<Habit>> habits = repository.getAll();
        final HabitModelList modelList = new HabitModelList();
        this.habits = Transformations.map(habits, input -> input.stream()
                .map(model -> modelList.getModel(false, model, visibleDay))
                .collect(Collectors.toList()));
        idButton = new ButtonHidingModel(false, false, 0).getId();
        //repository.insert(new Habit("asd", 12, 123, 1, new boolean[]{true, true, true, true, true, true, true}, new ArrayList<>())); //todo remove
    }

    @Override
    public void setVisibleDay(Date visibleDay) {
        super.setVisibleDay(visibleDay);
        if (habits.getValue().size() > 0)
            repository.update(habits.getValue().get(0).getHabit());
    }

    @Override
    public void itemClicked(CaseModel model) {
        Habit habit = ((HabitModel) model).getHabit();
        view().startHabitActivity(habit);
    }

    @Override
    public void itemStateChanged(CaseModel model) {
        Habit habit = ((HabitModel) model).getHabit();
        repository.update(habit);
    }

    @Override
    public void buttonHidingStateChanged(ButtonHidingModel button) {
        showCompleted = button.isChecked();
        buildDisplayedList(habits.getValue());
    }

    @Override
    protected void updateView() {
        habits.observe(view(), this::buildDisplayedList);
    }

    private void buildDisplayedList(List<HabitModel> habits) {
        final List<BaseModel> models = new ArrayList<>();
        final List<HabitModel> forDay = getPlannedForDay(habits, visibleDay);
        final List<HabitModel> unfulfilled = getUnfulfilledOnDay(forDay, visibleDay);
        final int countCompleted = forDay.size() - unfulfilled.size();
        if (showCompleted) {
            models.addAll(forDay);
        }
        else {
            models.addAll(unfulfilled);
        }
        if (countCompleted > 0) {
            models.add(new ButtonHidingModel(idButton, false, showCompleted, countCompleted));
        }
        view().setSortedList(models);
    }

    private List<HabitModel> getPlannedForDay(List<HabitModel> models, Date day) {
        return models.stream()
                .filter(m -> m.getHabit().plannedForDay(day))
                .collect(Collectors.toList());
    }

    private List<HabitModel> getUnfulfilledOnDay(List<HabitModel> models, Date day) {
        return models.stream()
                .filter(m -> !m.getHabit().isCompleteOnDay(day))
                .collect(Collectors.toList());
    }
}