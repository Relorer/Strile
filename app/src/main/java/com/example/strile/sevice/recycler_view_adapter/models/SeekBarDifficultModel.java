package com.example.strile.sevice.recycler_view_adapter.models;

public class SeekBarDifficultModel extends BaseModel {

    private int progress;

    @Override
    public int getType() {
        return BaseModel.SEEK_BAR_DIFFICULT_TYPE;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
