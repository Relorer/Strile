package com.example.strile.activities.settings.pomodoro_timer;

import com.example.strile.R;
import com.example.strile.service.presenter.BasePresenter;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.number_edit_with_title.NumberEditWIthTitleModel;
import com.example.strile.service.settings.UsersSettings;

import java.util.ArrayList;
import java.util.List;

public class SettingsPomodoroTimerPresenter extends BasePresenter<SettingsPomodoroTimerActivity> {

    private final int pomodoroId;
    private final int shortBreakId;
    private final int longBreakId;
    private final int delyId;

    public SettingsPomodoroTimerPresenter() {
        pomodoroId = new NumberEditWIthTitleModel(false, "", "", 0, 0, 0).getId();
        shortBreakId = new NumberEditWIthTitleModel(false, "", "", 0, 0, 0).getId();
        longBreakId = new NumberEditWIthTitleModel(false, "", "", 0, 0, 0).getId();
        delyId = new NumberEditWIthTitleModel(false, "", "", 0, 0, 0).getId();
    }

    @Override
    protected void updateView() {
        List<BaseModel> models = new ArrayList<>();

        int count = (int) (UsersSettings.getTimerPomodoroTimeGoal() / 60_000);
        String minutes = view().getResources().getQuantityString(R.plurals.minutes_without_number, count);
        models.add(new NumberEditWIthTitleModel(pomodoroId, false,
                view().getString(R.string.pomodoro_duration), minutes, 60, 1, count));

        count = (int) (UsersSettings.getTimerShortBreakTimeGoal() / 60_000);
        minutes = view().getResources().getQuantityString(R.plurals.minutes_without_number, count);
        models.add(new NumberEditWIthTitleModel(shortBreakId, true,
                view().getString(R.string.short_break_duration), minutes, 60, 1, count));

        count = (int) (UsersSettings.getTimerLongBreakTimeGoal() / 60_000);
        minutes = view().getResources().getQuantityString(R.plurals.minutes_without_number, count);
        models.add(new NumberEditWIthTitleModel(longBreakId, true,
                view().getString(R.string.long_break_duration), minutes, 60, 1, count));

        count = UsersSettings.getTimerFrequencyLongBreak();
        String pomodoro = view().getResources().getQuantityString(R.plurals.pomodoros_without_number, count);
        models.add(new NumberEditWIthTitleModel(delyId, true,
                view().getString(R.string.long_break_delay), pomodoro, 20, 1, count));

        view().setSortedList(models);
    }

    public void backButtonClicked() {
        view().finish();
    }

    public void numberChanged(NumberEditWIthTitleModel model) {
        String postfix = "";
        if (model.getId() == pomodoroId) {
            UsersSettings.setTimerPomodoroTimeGoal(model.getNumber() * 60_000);
            postfix = view().getResources().getQuantityString(R.plurals.minutes_without_number, model.getNumber());
        } else if (model.getId() == shortBreakId) {
            UsersSettings.setTimerShortBreakTimeGoal(model.getNumber() * 60_000);
            postfix = view().getResources().getQuantityString(R.plurals.minutes_without_number, model.getNumber());
        } else if (model.getId() == longBreakId) {
            UsersSettings.setTimerLongBreakTimeGoal(model.getNumber() * 60_000);
            postfix = view().getResources().getQuantityString(R.plurals.minutes_without_number, model.getNumber());
        } else if (model.getId() == delyId) {
            UsersSettings.setTimerFrequencyLongBreak(model.getNumber());
            postfix = view().getResources().getQuantityString(R.plurals.pomodoros_without_number, model.getNumber());
        }
        model.setPostfix(postfix);
    }
}
