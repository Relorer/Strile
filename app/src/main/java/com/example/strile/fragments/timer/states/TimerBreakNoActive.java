package com.example.strile.fragments.timer.states;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.example.strile.R;
import com.example.strile.sevice.timer.TimerController;
import com.example.strile.sevice.timer.TimerState;
import com.example.strile.sevice.timer.TimerView;

public class TimerBreakNoActive implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final int numPom;
    private final long goalTimeShort = 5 * 60000; //todo put in the settings
    private final long goalTimeLong = 15 * 60000;
    private final long frequencyLongBreak = 4;

    public TimerBreakNoActive(TimerView view, TimerController timer, int numPom) {
        this.view = view;
        this.timer = timer;
        this.numPom = numPom;
        bindView();
    }

    @Override
    public void primaryButtonClicked() {
        if (numPom % frequencyLongBreak == 0) {
            timer.setState(new TimerBreakRunning(view, timer, numPom, goalTimeLong));
        }
        else {
            timer.setState(new TimerBreakRunning(view, timer, numPom, goalTimeShort));
        }
    }

    @Override
    public void secondaryButtonClicked() {
        timer.setState(new TimerPomodoroNoActive(view, timer, numPom));
    }

    private void bindView() {
        final Activity context = ((Fragment) view).getActivity();
        if (numPom % frequencyLongBreak == 0) {
            view.setTextItemTitle(context.getString(R.string.take_a_long_break));
            view.setTotalTimeOnCanvas(goalTimeLong);
            view.setCurrentTimeOnCanvas(goalTimeLong);
            view.setTextTime(goalTimeLong);
        }
        else {
            view.setTextItemTitle(context.getString(R.string.take_a_short_break));
            view.setTotalTimeOnCanvas(goalTimeShort);
            view.setCurrentTimeOnCanvas(goalTimeShort);
            view.setTextTime(goalTimeShort);
        }
        view.setTextInfo("");
        view.setTextButtonTimerControlPrimary(context.getString(R.string.start));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.skip));
    }
}
