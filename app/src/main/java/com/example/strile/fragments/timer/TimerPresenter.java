package com.example.strile.fragments.timer;

import com.example.strile.fragments.timer.states.TimerPomodoroNoActive;
import com.example.strile.sevice.timer.TimerController;
import com.example.strile.sevice.timer.TimerState;
import com.example.strile.sevice.presenter.BasePresenter;

public class TimerPresenter extends BasePresenter<TimerFragment> implements TimerController {

    private TimerState state;

    @Override
    protected void updateView() {
        state = new TimerPomodoroNoActive(view(), this, 1);
    }

    public void buttonTimerControlSecondaryClicked() {
        state.secondaryButtonClicked();
    }

    public void buttonTimerControlPrimaryClicked() {
        state.primaryButtonClicked();
    }

    @Override
    public void setState(TimerState state) {
        this.state = state;
    }
}