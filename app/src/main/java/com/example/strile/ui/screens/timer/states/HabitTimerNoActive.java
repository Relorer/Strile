package com.example.strile.ui.screens.timer.states;

import android.app.Activity;

import com.example.strile.R;
import com.example.strile.data.entities.Habit;
import com.example.strile.infrastructure.timer.TimerController;
import com.example.strile.infrastructure.timer.TimerState;
import com.example.strile.infrastructure.timer.TimerView;

public class HabitTimerNoActive implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final Habit habit;

    public HabitTimerNoActive(TimerView view, TimerController timer, Habit habit) {
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