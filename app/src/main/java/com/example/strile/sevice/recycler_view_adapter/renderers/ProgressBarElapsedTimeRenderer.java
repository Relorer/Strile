package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ProgressBarElapsedTimeHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;

public class ProgressBarElapsedTimeRenderer extends BaseRenderer<ProgressBarElapsedTimeModel, ProgressBarElapsedTimeHolder> {
    public ProgressBarElapsedTimeRenderer(OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ProgressBarElapsedTimeHolder createViewHolder(@Nullable ViewGroup parent) {
        return new ProgressBarElapsedTimeHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_elapsed_time, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.PROGRESS_BAR_ELAPSED_TIME_TYPE;
    }
}
