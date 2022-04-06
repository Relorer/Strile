package com.example.strile.ui.screens.timer.states;

import android.app.Activity;

import com.example.strile.R;
import com.example.strile.data.entities.Habit;
import com.example.strile.infrastructure.timer.TimerController;
import com.example.strile.infrastructure.timer.TimerState;
import com.example.strile.infrastructure.timer.TimerView;

public class HabitTimerPause implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final Habit habit;

    public HabitTimerPause(TimerView view, TimerController timer, Habit habit) {
        this.view = view;
        this.timer = timer;
        this.habit = habit;
        bindView();
    }

    @Override
    public void primaryButtonClicked() {
        timer.setState(new HabitTimerRunning(view, timer, habit));
    }

    @Override
    public void secondaryButtonClicked() {
        habit.setElapsedTime(habit.getGoalTime());
        ((Activity) view).finish();
    }

    private void bindView() {
        final Activity context = ((Activity) view);
        view.setTextButtonTimerControlPrimary(context.getString(R.string.resume));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.done));
    }
}