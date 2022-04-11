package com.example.strile.infrastructure.rvadapter.adapters;


import com.example.strile.infrastructure.rvadapter.items.button_auth.ButtonAuthRenderer;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_night_mode_selection.ButtonNightModeSelectionRenderer;
import com.example.strile.infrastructure.rvadapter.items.button_pomodoro_timer_settings.ButtonPomodoroTimerSettingsRenderer;
import com.example.strile.infrastructure.rvadapter.items.button_task_time_notify_selection.ButtonTaskTimeNotifySelectionRenderer;
import com.example.strile.infrastructure.rvadapter.items.number_edit_with_title.NumberEditWIthTitleRenderer;
import com.example.strile.infrastructure.rvadapter.items.switch_setting.SwitchSettingRenderer;

public class SettingsListAdapter extends BaseRecyclerViewAdapter {

    public SettingsListAdapter(OnModelChangedListener<BaseModel> onModelChangedListener, OnClickListener<BaseModel> onClickListener) {
        registerRenderer(new ButtonNightModeSelectionRenderer(onModelChangedListener));
        registerRenderer(new ButtonPomodoroTimerSettingsRenderer(onModelChangedListener, onClickListener));
        registerRenderer(new NumberEditWIthTitleRenderer(onModelChangedListener));
        registerRenderer(new SwitchSettingRenderer(onModelChangedListener));
        registerRenderer(new ButtonTaskTimeNotifySelectionRenderer(onModelChangedListener));
        registerRenderer(new ButtonAuthRenderer(onModelChangedListener, onClickListener));
    }
}
