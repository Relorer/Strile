package com.example.strile.sevice.recycler_view_adapter.models;

public class ProgressBarElapsedTimeModel extends BaseModel {

    private final int max;
    private int progress;

    public ProgressBarElapsedTimeModel(boolean topMargin, int max, int progress) {
        super(topMargin);
        this.max = max;
        this.progress = progress;
    }

    @Override
    public int getType() {
        return BaseModel.PROGRESS_BAR_ELAPSED_TIME_TYPE;
    }

    public int getProgress() {
        return progress;
    }

    public int getMax() {
        return max;
    }

    public ProgressBarElapsedTimeModel setProgress(int progress) {
        this.progress = progress;
        return this;
    }
}
