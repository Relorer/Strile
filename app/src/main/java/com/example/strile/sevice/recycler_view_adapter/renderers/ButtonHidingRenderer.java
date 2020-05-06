package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ButtonHidingHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;

public class ButtonHidingRenderer extends BaseRenderer<ButtonHidingModel, ButtonHidingHolder> {

    public ButtonHidingRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonHidingHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonHidingHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_hiding, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_HIDING_TYPE;
    }
}
