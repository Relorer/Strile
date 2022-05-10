package com.example.strile.ui.screens.main.journal.cases.habits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.strile.data_firebase.models.Habit;
import com.example.strile.data_firebase.repositories.HabitRepository;
import com.example.strile.ui.screens.main.journal.JournalFragment;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesPresenter;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.CaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_hiding.ButtonHidingModel;
import com.example.strile.infrastructure.rvadapter.items.habit.HabitModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JournalHabitsPresenter extends JournalCasesPresenter<JournalHabitsFragment> {

    private final HabitRepository repository;
    private final LiveData<List<HabitModel>> habitModels;
    private final LiveData<List<Habit>> habits;

    private List<HabitModel> updatedList;
    private final HabitModelList modelList;

    public JournalHabitsPresenter() {
        repository = new HabitRepository();
        habits = repository.getAll();
        modelList = new HabitModelList();
        this.habitModels = Transformations.map(habits, input -> input.stream()
                .map(model -> modelList.getModel(false, model, visibleDay))
                .collect(Collectors.toList()));
    }

    @Override
    public void setVisibleDay(Date visibleDay) {
        super.setVisibleDay(visibleDay);
        if (habitModels.getValue() != null && habitModels.getValue().size() > 0)
            buildDisplayedList(Objects.requireNonNull(this.habits.getValue()).stream().map(model -> modelList.getModel(false, model, visibleDay)).collect(Collectors.toList()));
    }

    @Override
    public void itemClicked(CaseModel model) {
        Habit habit = ((HabitModel) model).getHabit();

        JournalFragment fragment = (JournalFragment) view().getParentFragment();
        if (fragment != null)
            fragment.dismissSnackbar();

        view().startHabitActivity(habit.getId());
    }

    @Override
    public void itemStateChanged(CaseModel model) {
        Habit habit = ((HabitModel) model).getHabit();
        repository.update(habit);
        repository.updateExecuted(habit, ((HabitModel) model).isComplete());
    }

    @Override
    public void buttonHidingStateChanged(ButtonHidingModel button) {
        super.buttonHidingStateChanged(button);
        buildDisplayedList(Objects.requireNonNull(this.habits.getValue()).stream().map(model -> modelList.getModel(false, model, visibleDay)).collect(Collectors.toList()));
    }

    @Override
    protected void updateView() {
        if (updatedList != null) buildDisplayedList(updatedList);
        if (!habitModels.hasActiveObservers())
            habitModels.observe(view(), this::buildDisplayedList);
    }

    private void buildDisplayedList(List<HabitModel> habits) {
        updatedList = habits;

        if (view() != null) {
            final List<BaseModel> models = new ArrayList<>();
            final List<HabitModel> forDay = getPlannedForDay(habits, visibleDay);
            final List<HabitModel> unfulfilled = getUnfulfilledOnDay(forDay, visibleDay);
            final int countCompleted = forDay.size() - unfulfilled.size();
            if (showCompleted) {
                models.addAll(forDay);
            } else {
                models.addAll(unfulfilled);
            }
            if (countCompleted > 0) {
                models.add(new ButtonHidingModel(idButton, false, showCompleted, countCompleted));
            }
            view().setSortedList(models);

            updatedList = null;
        }
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