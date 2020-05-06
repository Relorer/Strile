package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ButtonAddSubtaskHolder;
import com.example.strile.sevice.recycler_view_adapter.holders.ButtonDateSelectionHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;

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
