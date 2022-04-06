package com.example.strile.infrastructure.rvadapter.items.button_hiding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

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
