package com.example.strile.fragments.timer.states;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.icu.util.ValueIterator;

import androidx.fragment.app.Fragment;

import com.example.strile.R;
import com.example.strile.sevice.TimerController;
import com.example.strile.sevice.TimerState;
import com.example.strile.sevice.TimerView;

public class TimerPomodoroNoActive implements TimerState {

    private final TimerView view;
    private final TimerController timer;
    private final int numPom;
    private final long goalTime = 25 * 60000; //todo put in the settings

    public TimerPomodoroNoActive(TimerView view, TimerController timer, int numPom) {
        this.view = view;
        this.timer = timer;
        this.numPom = numPom;
        bindView();
    }

    @Override
    public void primaryButtonClicked() {
        timer.setState(new TimerPomodoroRunning(view, timer, numPom, goalTime));
    }

    @Override
    public void secondaryButtonClicked() {

    }

    @SuppressLint("DefaultLocale")
    private void bindView() {
        final Activity context = ((Fragment) view).getActivity();
        view.setTextItemTitle(context.getString(R.string.pomodoro));
        view.setTextInfo(String.format("#%d", numPom));
        view.setTotalTimeOnCanvas(goalTime);
        view.setCurrentTimeOnCanvas(goalTime);
        view.setTextTime(goalTime);
        view.setTextButtonTimerControlPrimary(context.getString(R.string.start));
        view.setTextButtonTimerControlSecondary(context.getString(R.string.lets_go));
    }
}