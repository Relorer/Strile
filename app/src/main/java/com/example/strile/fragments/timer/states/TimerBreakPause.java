package com.example.strile.fragments.timer.states;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.example.strile.R;
import com.example.strile.service.timer.TimerController;
import com.example.strile.service.timer.TimerState;
import com.example.strile.service.timer.TimerView;

public class TimerBreakPause implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final int numPom;
    private final long remaining;

    public TimerBreakPause(TimerView view, TimerController timer, int numPom, long remaining) {
        this.view = view;
        this.timer = timer;
        this.numPom = numPom;
        this.remaining = remaining;
        bindView();
    }

    @Override
    public void primaryButtonClicked() {
        timer.setState(new TimerBreakRunning(view, timer, numPom, remaining));
    }

    @Override
    public void secondaryButtonClicked() {
        timer.setState(new TimerPomodoroNoActive(view, timer, numPom));
    }

    private void bindView() {
        final Activity context = ((Fragment) view).getActivity();
        assert context != null;
        view.setTextButtonTimerControlPrimary(context.getString(R.string.resume));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.skip));
    }
}
