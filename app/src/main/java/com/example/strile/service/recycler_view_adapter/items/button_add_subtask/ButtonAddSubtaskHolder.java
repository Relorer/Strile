package com.example.strile.service.recycler_view_adapter.items.button_add_subtask;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.strile.service.event_handler_interfaces.OnClickListener;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseHolder;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;

public class ButtonAddSubtaskHolder extends BaseHolder<ButtonAddSubtaskModel> {

    public ButtonAddSubtaskHolder(@NonNull View itemView,
                                  @NonNull final OnModelChangedListener<ButtonAddSubtaskModel> onModelChangedListener,
                                  @NonNull final OnClickListener<BaseModel> onClickListener) {
        super(itemView, onModelChangedListener);
        itemView.setOnClickListener(v -> onClickListener.onClick(model));
    }
}