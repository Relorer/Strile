package com.example.strile.fragments.timer.states;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.example.strile.R;
import com.example.strile.sevice.timer.TimerController;
import com.example.strile.sevice.timer.TimerState;
import com.example.strile.sevice.timer.TimerView;

public class TimerPomodoroPause implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final int numPom;
    private final long remaining;
    private final long goalTime = 25 * 60000; //todo put in the settings

    public TimerPomodoroPause(TimerView view, TimerController timer, int numPom, long remaining) {
        this.view = view;
        this.timer = timer;
        this.numPom = numPom;
        this.remaining = remaining;
        bindView();
    }

    @Override
    public void primaryButtonClicked() {
        timer.setState(new TimerPomodoroRunning(view, timer, numPom, remaining));
    }

    @Override
    public void secondaryButtonClicked() {
        timer.setState(new TimerBreakNoActive(view, timer, numPom + 1));
    }

    private void bindView() {
        final Activity context = ((Fragment)view).getActivity();
        view.setTextButtonTimerControlPrimary(context.getString(R.string.resume));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.done));
    }
}
