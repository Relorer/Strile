package com.example.strile.infrastructure.rvadapter.items.button_add_subtask;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class ButtonAddSubtaskRenderer extends BaseRenderer<ButtonAddSubtaskModel, ButtonAddSubtaskHolder> {

    private final OnClickListener onClickListener;

    public ButtonAddSubtaskRenderer(@NonNull OnModelChangedListener onModelChangedListener,
                                    @NonNull OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ButtonAddSubtaskHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonAddSubtaskHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_icon_name, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_ADD_SUBTASK_TYPE;
    }
}
