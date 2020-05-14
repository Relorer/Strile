package com.example.strile.activities.timer.states;

import android.app.Activity;

import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.TimerController;
import com.example.strile.sevice.TimerState;
import com.example.strile.sevice.TimerView;

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