package com.example.strile.service.recycler_view_adapter.items.subtask;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.BaseRenderer;

public class SubtaskRenderer extends BaseRenderer<SubtaskModel, SubtaskHolder> {

    public SubtaskRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public SubtaskHolder createViewHolder(@NonNull ViewGroup parent) {
        return new SubtaskHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subtask, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.SUBTASK_TYPE;
    }
}