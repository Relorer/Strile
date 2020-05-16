package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ExecutedHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ExecutedModel;

public class ExecutedRenderer extends BaseRenderer<ExecutedModel, ExecutedHolder> {

    public ExecutedRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ExecutedHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ExecutedHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_executed, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.EXECUTED_TYPE;
    }
}
