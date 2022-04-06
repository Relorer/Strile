package com.example.strile.ui.screens.settings;

import android.os.Build;

import com.example.strile.R;
import com.example.strile.infrastructure.presenter.BasePresenter;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_night_mode_selection.ButtonNightModeSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.button_pomodoro_timer_settings.ButtonPomodoroTimerSettingsModel;
import com.example.strile.infrastructure.rvadapter.items.button_task_time_notify_selection.ButtonTaskTimeNotifySelectionModel;
import com.example.strile.infrastructure.rvadapter.items.switch_setting.SwitchSettingModel;
import com.example.strile.infrastructure.settings.UsersSettings;

import java.util.ArrayList;
import java.util.List;

public class SettingsPresenter extends BasePresenter<SettingsActivity> {

    private final int buttonNightModeId;
    private final int buttonPomodoroTimerSettingsId;
    private final int buttonTaskNotifyTimeSettingsId;
    private final int switchNotifyAfterTimerId;

    public SettingsPresenter() {
        buttonNightModeId = new ButtonNightModeSelectionModel(false, 0).getId();
        buttonPomodoroTimerSettingsId = new ButtonPomodoroTimerSettingsModel(false,
                0, 0, 0, 0).getId();
        switchNotifyAfterTimerId = new SwitchSettingModel(false, "", 0, false).getId();
        buttonTaskNotifyTimeSettingsId = new ButtonTaskTimeNotifySelectionModel(false, 0).getId();
    }

    @Override
    protected void updateView() {
        final List<BaseModel> models = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            final int nightMode = UsersSettings.getNightMode();
            models.add(new ButtonNightModeSelectionModel(buttonNightModeId, false, nightMode));
        }

        final long pomodoroTimeGoal = UsersSettings.getTimerPomodoroTimeGoal();
        final long shortBreakTimeGoal = UsersSettings.getTimerShortBreakTimeGoal();
        final long longBreakTimeGoal = UsersSettings.getTimerLongBreakTimeGoal();
        final int frequencyLongBreak = UsersSettings.getTimerFrequencyLongBreak();
        models.add(new ButtonPomodoroTimerSettingsModel(buttonPomodoroTimerSettingsId, false,
                pomodoroTimeGoal, shortBreakTimeGoal, longBreakTimeGoal, frequencyLongBreak));

        final long taskTimeNotify = UsersSettings.getTimeNotifyUpcomingTask();
        models.add(new ButtonTaskTimeNotifySelectionModel(buttonTaskNotifyTimeSettingsId, false, taskTimeNotify));

        final boolean timerNotify = UsersSettings.isNotifyAfterTimerEnd();
        models.add(new SwitchSettingModel(switchNotifyAfterTimerId, true,
                view().getString(R.string.notify_after_timer_ends), R.drawable.notifications, timerNotify));

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

    public void taskTimeNotifyChanged(ButtonTaskTimeNotifySelectionModel model) {
        UsersSettings.setTimeNotifyUpcomingTask(model.getTime());
    }

    public void switchStateChanged(SwitchSettingModel model) {
        if (model.getId() == switchNotifyAfterTimerId) {
            UsersSettings.setNotifyAfterTimerEnds(model.isChecked());
        }
    }
}