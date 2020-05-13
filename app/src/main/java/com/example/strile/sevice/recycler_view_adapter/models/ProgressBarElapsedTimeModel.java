package com.example.strile.sevice.recycler_view_adapter.models;

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

    public int getMax() {
        return max;
    }

    public ProgressBarElapsedTimeModel setProgress(int progress) {
        this.progress = progress;
        return this;
    }

    public ProgressBarElapsedTimeModel setMax(int max) {
        this.max = max;
        return this;
    }
}
