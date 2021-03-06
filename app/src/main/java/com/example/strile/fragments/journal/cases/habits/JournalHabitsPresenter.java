package com.example.strile.fragments.journal.cases.habits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.strile.App;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.fragments.journal.JournalFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.CaseModel;
import com.example.strile.service.recycler_view_adapter.items.button_hiding.ButtonHidingModel;
import com.example.strile.service.recycler_view_adapter.items.habit.HabitModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JournalHabitsPresenter extends JournalCasesPresenter<JournalHabitsFragment> {

    private final Repository<Habit> repository;
    private final LiveData<List<HabitModel>> habits;

    private List<HabitModel> updatedList;

    public JournalHabitsPresenter() {
        repository = new HabitRepository(App.getInstance());
        final LiveData<List<Habit>> habits = repository.getAll();
        final HabitModelList modelList = new HabitModelList();
        this.habits = Transformations.map(habits, input -> input.stream()
                .map(model -> modelList.getModel(false, model, visibleDay))
                .collect(Collectors.toList()));
    }

    @Override
    public void setVisibleDay(Date visibleDay) {
        super.setVisibleDay(visibleDay);
        if (habits.getValue() != null && habits.getValue().size() > 0)
            repository.update(habits.getValue().get(0).getHabit());
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
    }

    @Override
    public void buttonHidingStateChanged(ButtonHidingModel button) {
        super.buttonHidingStateChanged(button);
        buildDisplayedList(habits.getValue());
    }

    @Override
    protected void updateView() {
        if (updatedList != null) buildDisplayedList(updatedList);
        if (!habits.hasActiveObservers())
            habits.observe(view(), this::buildDisplayedList);
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