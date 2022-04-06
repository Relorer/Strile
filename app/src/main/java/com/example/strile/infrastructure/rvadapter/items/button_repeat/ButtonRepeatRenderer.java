package com.example.strile.infrastructure.rvadapter.items.button_repeat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class ButtonRepeatRenderer extends BaseRenderer<ButtonRepeatModel, ButtonRepeatHolder> {

    public ButtonRepeatRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonRepeatHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonRepeatHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_icon_name, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_REPEAT_TYPE;
    }
}
