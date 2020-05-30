package com.example.strile.service.recycler_view_adapter.items.button_task_time_notify_selection;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.BaseRenderer;

public class ButtonTaskTimeNotifySelectionRenderer extends BaseRenderer<ButtonTaskTimeNotifySelectionModel, ButtonTaskTimeNotifySelectionHolder> {

    public ButtonTaskTimeNotifySelectionRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonTaskTimeNotifySelectionHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonTaskTimeNotifySelectionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setting_with_info_and_icon, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_TASK_TIME_NOTIFY_SELECTION_TYPE;
    }
}
