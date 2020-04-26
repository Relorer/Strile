package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ButtonAddSubtaskHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;

public class ButtonAddSubtaskRenderer extends BaseRenderer<ButtonAddSubtaskModel, ButtonAddSubtaskHolder> {

    public OnClickListener onClickListener;

    public ButtonAddSubtaskRenderer(OnModelChangedListener onModelChangedListener, OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ButtonAddSubtaskHolder createViewHolder(@Nullable ViewGroup parent) {
        return new ButtonAddSubtaskHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_add_subtask, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_ADD_SUBTASK_TYPE;
    }
}
