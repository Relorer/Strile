package com.example.strile.activities.timer.timer_states;

import android.app.Activity;

import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.TimerView;

public class TimerPause implements HabitTimerState {

    private final TimerView view;
    private final HabitTimer timer;
    private final Habit habit;

    public TimerPause(TimerView view, HabitTimer timer, Habit habit) {
        this.view = view;
        this.timer = timer;
        this.habit = habit;
        bindView();
    }

    @Override
    public void primaryButtonClicked() {
        timer.setState(new TimerRunning(view, timer, habit));
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