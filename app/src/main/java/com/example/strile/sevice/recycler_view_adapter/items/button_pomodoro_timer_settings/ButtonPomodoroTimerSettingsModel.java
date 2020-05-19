package com.example.strile.sevice.recycler_view_adapter.items.button_pomodoro_timer_settings;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class ButtonPomodoroTimerSettingsModel extends BaseModel {

    private long pomodoroTimeGoal;
    private long shortBreakTimeGoal;
    private long longBreakTimeGoal;
    private int frequencyLongBreak;

    public ButtonPomodoroTimerSettingsModel(boolean topMargin, long pomodoroTimeGoal,
                                            long shortBreakTimeGoal, long longBreakTimeGoal, int frequencyLongBreak) {
        super(topMargin);
        this.pomodoroTimeGoal = pomodoroTimeGoal;
        this.shortBreakTimeGoal = shortBreakTimeGoal;
        this.longBreakTimeGoal = longBreakTimeGoal;
        this.frequencyLongBreak = frequencyLongBreak;
    }

    public ButtonPomodoroTimerSettingsModel(int id, boolean topMargin, long pomodoroTimeGoal,
                                            long shortBreakTimeGoal, long longBreakTimeGoal, int frequencyLongBreak) {
        super(id, topMargin);
        this.pomodoroTimeGoal = pomodoroTimeGoal;
        this.shortBreakTimeGoal = shortBreakTimeGoal;
        this.longBreakTimeGoal = longBreakTimeGoal;
        this.frequencyLongBreak = frequencyLongBreak;
    }

    public long getPomodoroTimeGoal() {
        return pomodoroTimeGoal;
    }

    public ButtonPomodoroTimerSettingsModel setPomodoroTimeGoal(long pomodoroTimeGoal) {
        this.pomodoroTimeGoal = pomodoroTimeGoal;
        return this;
    }

    public long getShortBreakTimeGoal() {
        return shortBreakTimeGoal;
    }

    public ButtonPomodoroTimerSettingsModel setShortBreakTimeGoal(long shortBreakTimeGoal) {
        this.shortBreakTimeGoal = shortBreakTimeGoal;
        return this;
    }

    public long getLongBreakTimeGoal() {
        return longBreakTimeGoal;
    }

    public ButtonPomodoroTimerSettingsModel setLongBreakTimeGoal(long longBreakTimeGoal) {
        this.longBreakTimeGoal = longBreakTimeGoal;
        return this;
    }

    public int getFrequencyLongBreak() {
        return frequencyLongBreak;
    }

    public ButtonPomodoroTimerSettingsModel setFrequencyLongBreak(int frequencyLongBreak) {
        this.frequencyLongBreak = frequencyLongBreak;
        return this;
    }

    @Override
    public int getType() {
        return BUTTON_POMODORO_TIMER_SETTINGS_TYPE;
    }
}
