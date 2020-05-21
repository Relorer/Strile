package com.example.strile.fragments.timer.states;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import androidx.fragment.app.Fragment;

import com.example.strile.R;
import com.example.strile.service.notifications.NotificationDisplay;
import com.example.strile.service.timer.TimerController;
import com.example.strile.service.timer.TimerState;
import com.example.strile.service.timer.TimerView;

public class TimerBreakRunning implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final int numPom;
    private long remaining;

    private CountDownTimer countDownTimer;

    public TimerBreakRunning(TimerView view, TimerController timer, int numPom, long remaining) {
        this.view = view;
        this.timer = timer;
        this.numPom = numPom;
        this.remaining = remaining;
        bindView();
        startTimer();
    }

    @Override
    public void primaryButtonClicked() {
        countDownTimer.cancel();
        timer.setState(new TimerBreakPause(view, timer, numPom, remaining));
    }

    @Override
    public void secondaryButtonClicked() {
        countDownTimer.cancel();
        timer.setState(new TimerPomodoroNoActive(view, timer, numPom));
    }

    private void bindView() {
        final Activity context = ((Fragment) view).getActivity();
        assert context != null;
        view.setTextButtonTimerControlPrimary(context.getString(R.string.pause));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.skip));
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(remaining, 1) {
            private int time = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                remaining = millisUntilFinished;
                view.setCurrentTimeOnCanvas(millisUntilFinished);
                if (time != (int) (remaining / 1000))
                    view.setTextTime(millisUntilFinished);
                time = (int) (remaining / 1000);
            }

            @Override
            public void onFinish() {
                Context context = ((Fragment) view).getContext();
                assert context != null;
                //todo check settings params
                new NotificationDisplay(context)
                        .showNotification(context.getString(R.string.break_ended),
                                context.getString(R.string.break_ended_text), new Intent(), TIMER_ID);
                timer.setState(new TimerPomodoroNoActive(view, timer, numPom));
            }
        }.start();
    }
}