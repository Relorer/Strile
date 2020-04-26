package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonDateSelectionModel extends BaseModel {

    private long date;

    @Override
    public int getType() {
        return BaseModel.BUTTON_DATE_SELECTION_TYPE;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
