package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.SubtaskHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;

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