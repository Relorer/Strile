package com.example.strile.infrastructure.rvadapter.items.button_time_selection;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class ButtonTimeSelectionRenderer extends BaseRenderer<ButtonTimeSelectionModel, ButtonTimeSelectionHolder> {

    public ButtonTimeSelectionRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonTimeSelectionHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonTimeSelectionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_icon_name_cross, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_TIME_SELECTION_TYPE;
    }

}
