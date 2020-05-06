package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;

public class ButtonAddSubtaskHolder extends BaseHolder<ButtonAddSubtaskModel> {

    public ButtonAddSubtaskHolder(@NonNull View itemView,
                                  @NonNull final OnModelChangedListener<ButtonAddSubtaskModel> onModelChangedListener,
                                  @NonNull final OnClickListener<BaseModel> onClickListener) {
        super(itemView, onModelChangedListener);
        itemView.setOnClickListener(v -> {
            onClickListener.onClick(model);
        });
    }
}