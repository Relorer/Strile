package com.example.strile.ui.screens.timer;

import androidx.lifecycle.LiveData;

import com.example.strile.ui.screens.timer.states.HabitTimerNoActive;
import com.example.strile.data_firebase.models.Habit;
import com.example.strile.data_firebase.repositories.HabitRepository;
import com.example.strile.infrastructure.presenter.BasePresenter;
import com.example.strile.infrastructure.timer.TimerController;
import com.example.strile.infrastructure.timer.TimerState;

public class HabitTimerPresenter extends BasePresenter<HabitTimerActivity> implements TimerController {

    private final HabitRepository repository;
    private final LiveData<Habit> habit;

    private TimerState state;

    public HabitTimerPresenter(String habitId) {
        repository = new HabitRepository();
        habit = repository.getById(habitId);
    }

    public void backButtonClicked() {
        view().finish();
    }

    @Override
    public void unbindView() {
        super.unbindView();
        updateHabit(habit.getValue());
    }

    @Override
    protected void updateView() {
        if (!habit.hasActiveObservers() && state == null) {
            habit.observe(view(), habit -> {
                if (habit != null && view() != null) {
                    view().setTextItemTitle(habit.getName());
                    if (state == null)
                        state = new HabitTimerNoActive(view(), this, habit);
                }
               this.habit.removeObservers(view());
            });
        }
    }

    public void buttonTimerControlSecondaryClicked() {
        state.secondaryButtonClicked();
    }

    public void buttonTimerControlPrimaryClicked() {
        state.primaryButtonClicked();
    }

    @Override
    public void setState(TimerState state) {
        this.state = state;
    }

    public void updateHabit(Habit habit) {
        repository.update(habit);
    }
}