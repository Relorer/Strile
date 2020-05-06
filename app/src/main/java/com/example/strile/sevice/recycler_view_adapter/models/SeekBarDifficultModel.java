package com.example.strile.sevice.recycler_view_adapter.models;

public class SeekBarDifficultModel extends BaseModel {

    private final int progress;

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
}
