package com.example.strile.activities.timer.timer_states;

import android.app.Activity;

import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.TimerView;

public class TimerNoActive implements HabitTimerState {

    private final TimerView view;
    private final HabitTimer timer;
    private final Habit habit;

    public TimerNoActive(TimerView view, HabitTimer timer, Habit habit) {
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

    }

    private void bindView() {
        final Activity context = ((Activity) view);
        final long goal = habit.getGoalTime();
        final long elapsed = habit.getElapsedTime();
        view.setTextInfo(elapsed / 60000 + " / " + goal / 60000);
        view.setCurrentTimeOnCanvas(goal - elapsed);
        view.setTotalTimeOnCanvas(goal);
        view.setTextTime(goal - elapsed);
        view.setTextButtonTimerControlPrimary(context.getString(R.string.start));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.lets_go));
    }
}