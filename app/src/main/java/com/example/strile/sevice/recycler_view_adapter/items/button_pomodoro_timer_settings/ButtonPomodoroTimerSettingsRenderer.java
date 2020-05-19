package com.example.strile.sevice.recycler_view_adapter.items.button_pomodoro_timer_settings;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.BaseRenderer;

public class ButtonPomodoroTimerSettingsRenderer extends BaseRenderer<ButtonPomodoroTimerSettingsModel, ButtonPomodoroTimerSettingsHolder> {

    private final OnClickListener onClickListener;

    public ButtonPomodoroTimerSettingsRenderer(@NonNull OnModelChangedListener onModelChangedListener,
                                    @NonNull OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ButtonPomodoroTimerSettingsHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonPomodoroTimerSettingsHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setting_with_info_and_icon, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_POMODORO_TIMER_SETTINGS_TYPE;
    }
}
