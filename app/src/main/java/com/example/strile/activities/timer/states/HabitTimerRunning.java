package com.example.strile.activities.timer.states;

import android.app.Activity;
import android.os.CountDownTimer;

import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.timer.TimerController;
import com.example.strile.sevice.timer.TimerState;
import com.example.strile.sevice.timer.TimerView;

public class HabitTimerRunning implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final Habit habit;

    private CountDownTimer countDownTimer;

    public HabitTimerRunning(TimerView view, TimerController timer, Habit habit) {
        this.view = view;
        this.timer = timer;
        this.habit = habit;
        bindView();
        startTimer();
    }

    @Override
    public void primaryButtonClicked() {
        countDownTimer.cancel();
        timer.setState(new HabitTimerPause(view, timer, habit));
    }

    @Override
    public void secondaryButtonClicked() {
        countDownTimer.cancel();
        habit.setElapsedTime(habit.getGoalTime());
        ((Activity) view).finish();
    }

    private void bindView() {
        final Activity context = ((Activity) view);
        view.setTextButtonTimerControlPrimary(context.getString(R.string.pause));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.done));
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(habit.getGoalTime() - habit.getElapsedTime(), 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                habit.setElapsedTime(habit.getGoalTime() - millisUntilFinished);

                final long goal = habit.getGoalTime();
                final long elapsed = habit.getElapsedTime();
                view.setTextInfo(elapsed / 60000 + " / " + goal / 60000);
                view.setCurrentTimeOnCanvas(goal - elapsed);
                view.setTextTime(goal - elapsed);
            }

            @Override
            public void onFinish() {
                habit.setElapsedTime(habit.getGoalTime());
                ((Activity)view).finish();
            }
        }.start();
    }
}