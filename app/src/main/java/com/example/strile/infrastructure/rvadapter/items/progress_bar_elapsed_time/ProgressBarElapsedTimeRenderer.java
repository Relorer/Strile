package com.example.strile.infrastructure.rvadapter.items.progress_bar_elapsed_time;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class ProgressBarElapsedTimeRenderer extends BaseRenderer<ProgressBarElapsedTimeModel, ProgressBarElapsedTimeHolder> {

    private final OnClickListener onClickListener;

    public ProgressBarElapsedTimeRenderer(@NonNull OnModelChangedListener onModelChangedListener,
                                          @NonNull OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ProgressBarElapsedTimeHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ProgressBarElapsedTimeHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.progress_bar_elapsed_time, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.PROGRESS_BAR_ELAPSED_TIME_TYPE;
    }
}
