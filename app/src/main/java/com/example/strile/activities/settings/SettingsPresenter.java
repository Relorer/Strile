package com.example.strile.activities.settings;

import android.os.Build;

import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.button_night_mode_selection.ButtonNightModeSelectionModel;
import com.example.strile.sevice.recycler_view_adapter.items.button_pomodoro_timer_settings.ButtonPomodoroTimerSettingsModel;
import com.example.strile.sevice.settings.UsersSettings;

import java.util.ArrayList;
import java.util.List;

public class SettingsPresenter extends BasePresenter<SettingsActivity> {

    private final int buttonNightModeId;
    private final int buttonPomodoroTimerSettingsId;

    public SettingsPresenter() {
        buttonNightModeId = new ButtonNightModeSelectionModel(false, 0).getId();
        buttonPomodoroTimerSettingsId = new ButtonPomodoroTimerSettingsModel(false,
                0, 0, 0, 0).getId();
    }

    @Override
    protected void updateView() {
        List<BaseModel> models = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            final int nightMode = UsersSettings.getNightMode();
            models.add(new ButtonNightModeSelectionModel(buttonNightModeId, false, nightMode));
        }
        long pomodoroTimeGoal = UsersSettings.getTimerPomodoroTimeGoal();
        long shortBreakTimeGoal = UsersSettings.getTimerShortBreakTimeGoal();
        long longBreakTimeGoal = UsersSettings.getTimerLongBreakTimeGoal();
        int frequencyLongBreak = UsersSettings.getTimerFrequencyLongBreak();
        models.add(new ButtonPomodoroTimerSettingsModel(buttonPomodoroTimerSettingsId, models.size() > 0,
                pomodoroTimeGoal, shortBreakTimeGoal, longBreakTimeGoal, frequencyLongBreak));
        view().setSortedList(models);
    }

    public void backButtonClicked() {
        view().finish();
    }

    public void nightModeChanged(ButtonNightModeSelectionModel model) {
        UsersSettings.setNightMode(model.getNightMode());
        view().getDelegate().applyDayNight();
    }

    public void buttonPomodoroTimerSettingsClicked(ButtonPomodoroTimerSettingsModel sender) {
        view().startPomodoroTimerSettingsActivity();
    }
}
