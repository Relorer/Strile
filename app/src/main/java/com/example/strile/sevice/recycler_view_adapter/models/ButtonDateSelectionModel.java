package com.example.strile.sevice.recycler_view_adapter.models;

import java.util.Date;

public class ButtonDateSelectionModel extends BaseModel {

    private Date date;

    public ButtonDateSelectionModel(boolean topMargin, Date date) {
        super(topMargin);
        this.date = date;
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_DATE_SELECTION_TYPE;
    }

    public Date getDate() {
        return date;
    }

    public ButtonDateSelectionModel setDate(Date date) {
        this.date = date;
        return this;
    }
}
