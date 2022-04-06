package com.example.strile.ui.screens.main.timer.states;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.example.strile.R;
import com.example.strile.infrastructure.timer.TimerController;
import com.example.strile.infrastructure.timer.TimerState;
import com.example.strile.infrastructure.timer.TimerView;

public class TimerPomodoroPause implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final int numPom;
    private final long remaining;

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
        final Activity context = ((Fragment) view).getActivity();
        assert context != null;
        view.setTextButtonTimerControlPrimary(context.getString(R.string.resume));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.done));
    }
}
