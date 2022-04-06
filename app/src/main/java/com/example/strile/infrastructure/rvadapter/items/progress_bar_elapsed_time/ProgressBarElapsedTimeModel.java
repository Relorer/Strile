package com.example.strile.infrastructure.rvadapter.items.progress_bar_elapsed_time;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class ProgressBarElapsedTimeModel extends BaseModel {

    private int max;
    private int progress;

    public ProgressBarElapsedTimeModel(boolean topMargin) {
        super(topMargin);
    }

    public ProgressBarElapsedTimeModel(int id, boolean topMargin) {
        super(id, topMargin);
    }

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
