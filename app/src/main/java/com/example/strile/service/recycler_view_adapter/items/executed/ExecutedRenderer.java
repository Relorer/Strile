package com.example.strile.service.recycler_view_adapter.items.executed;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.BaseRenderer;

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
