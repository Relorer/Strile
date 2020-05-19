package com.example.strile.sevice.recycler_view_adapter.items.button_date_selection;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.BaseRenderer;

public class ButtonDateSelectionRenderer extends BaseRenderer<ButtonDateSelectionModel, ButtonDateSelectionHolder> {

    public ButtonDateSelectionRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonDateSelectionHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonDateSelectionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_selection_date, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_DATE_SELECTION_TYPE;
    }
}
