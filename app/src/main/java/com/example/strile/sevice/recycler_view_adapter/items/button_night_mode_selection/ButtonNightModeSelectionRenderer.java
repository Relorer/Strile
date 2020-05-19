package com.example.strile.sevice.recycler_view_adapter.items.button_night_mode_selection;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.BaseRenderer;

public class ButtonNightModeSelectionRenderer extends BaseRenderer<ButtonNightModeSelectionModel, ButtonNightModeSelectionHolder> {

    public ButtonNightModeSelectionRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonNightModeSelectionHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonNightModeSelectionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setting_with_info_and_icon, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_NIGHT_MODE_SELECTION_TYPE;
    }
}
