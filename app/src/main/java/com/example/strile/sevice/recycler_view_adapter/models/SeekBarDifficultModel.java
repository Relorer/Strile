package com.example.strile.sevice.recycler_view_adapter.models;

public class SeekBarDifficultModel extends BaseModel {

    private int progress;

    public SeekBarDifficultModel(boolean topMargin) {
        super(topMargin);
        progress = 0;
    }

    public SeekBarDifficultModel(boolean topMargin, int progress) {
        super(topMargin);
        this.progress = progress;
    }

    @Override
    public int getType() {
        return BaseModel.SEEK_BAR_DIFFICULT_TYPE;
    }

    public int getProgress() {
        return progress;
    }

    public SeekBarDifficultModel setProgress(int progress) {
        this.progress = progress;
        return this;
    }
}
