package com.example.strile.fragments.timer;

import com.example.strile.fragments.timer.states.TimerPomodoroNoActive;
import com.example.strile.service.presenter.BasePresenter;
import com.example.strile.service.settings.UsersSettings;
import com.example.strile.service.timer.TimerController;
import com.example.strile.service.timer.TimerState;

public class TimerPresenter extends BasePresenter<TimerFragment> implements TimerController {

    private TimerState state;
    private long lastPomodoroTimeGoal = 0;
    private long lastShortBreakTimeGoal = 0;
    private long lastLongBreakTimeGoal = 0;
    private long lastLongBreakDelay = 0;

    @Override
    protected void updateView() {
        if (state == null ||
                lastPomodoroTimeGoal != UsersSettings.getTimerPomodoroTimeGoal() ||
                lastShortBreakTimeGoal != UsersSettings.getTimerShortBreakTimeGoal() ||
                lastLongBreakTimeGoal != UsersSettings.getTimerLongBreakTimeGoal() ||
                lastLongBreakDelay != UsersSettings.getTimerFrequencyLongBreak()) {

            lastPomodoroTimeGoal = UsersSettings.getTimerPomodoroTimeGoal();
            lastShortBreakTimeGoal = UsersSettings.getTimerShortBreakTimeGoal();
            lastLongBreakTimeGoal = UsersSettings.getTimerLongBreakTimeGoal();
            lastLongBreakDelay = UsersSettings.getTimerFrequencyLongBreak();

            state = new TimerPomodoroNoActive(view(), this, 1);
        }
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