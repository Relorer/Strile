package com.example.strile.service.recycler_view_adapter.adapters;


import com.example.strile.service.event_handler_interfaces.OnClickListener;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.button_night_mode_selection.ButtonNightModeSelectionRenderer;
import com.example.strile.service.recycler_view_adapter.items.button_pomodoro_timer_settings.ButtonPomodoroTimerSettingsRenderer;
import com.example.strile.service.recycler_view_adapter.items.number_edit_with_title.NumberEditWIthTitleRenderer;

public class SettingsListAdapter extends BaseRecyclerViewAdapter {

    public SettingsListAdapter(OnModelChangedListener<BaseModel> onModelChangedListener, OnClickListener<BaseModel> onClickListener) {
        registerRenderer(new ButtonNightModeSelectionRenderer(onModelChangedListener));
        registerRenderer(new ButtonPomodoroTimerSettingsRenderer(onModelChangedListener, onClickListener));
        registerRenderer(new NumberEditWIthTitleRenderer(onModelChangedListener));
    }
}
