package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.CompleteCaseHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.database.entities.CompleteCaseModel;

public class CompleteCaseRenderer extends BaseRenderer<CompleteCaseModel, CompleteCaseHolder> {
    public CompleteCaseRenderer(OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public CompleteCaseHolder createViewHolder(@Nullable ViewGroup parent) {
        return new CompleteCaseHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complete_case, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.COMPLETE_CASE_TYPE;
    }
}
