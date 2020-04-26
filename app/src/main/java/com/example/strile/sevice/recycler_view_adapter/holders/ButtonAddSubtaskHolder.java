package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;

public class ButtonAddSubtaskHolder extends BaseHolder<ButtonAddSubtaskModel> {

    private OnClickListener onClickListener;

    public ButtonAddSubtaskHolder(@NonNull View itemView,
                                  final OnModelChangedListener<ButtonAddSubtaskModel> onModelChangedListener,
                                  final OnClickListener<BaseModel> onClickListener) {
        super(itemView, onModelChangedListener);
        this.onClickListener = onClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null)
                    onClickListener.onClick(model);
            }
        });
    }
}