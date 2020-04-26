package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ButtonRepeatHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;

public class ButtonRepeatRenderer extends BaseRenderer<ButtonRepeatModel, ButtonRepeatHolder> {


    public ButtonRepeatRenderer(OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonRepeatHolder createViewHolder(@Nullable ViewGroup parent) {
        return new ButtonRepeatHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.button_repeat, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_REPEAT_TYPE;
    }
}
