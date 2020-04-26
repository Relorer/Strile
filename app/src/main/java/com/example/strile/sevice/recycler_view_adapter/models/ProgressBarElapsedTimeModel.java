package com.example.strile.sevice.recycler_view_adapter.models;

import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;

import java.util.Objects;

public class ProgressBarElapsedTimeModel extends BaseModel {

    private int max;
    private int progress;

    @Override
    public int getType() {
        return BaseModel.PROGRESS_BAR_ELAPSED_TIME_TYPE;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
