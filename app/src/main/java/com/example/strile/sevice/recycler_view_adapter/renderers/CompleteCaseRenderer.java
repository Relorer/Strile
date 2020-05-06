package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.database.entities.Executed;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ExecutedHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ExecutedModel;

public class CompleteCaseRenderer extends BaseRenderer<ExecutedModel, ExecutedHolder> {

    public CompleteCaseRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ExecutedHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ExecutedHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_complete_case, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.COMPLETE_CASE_TYPE;
    }
}
