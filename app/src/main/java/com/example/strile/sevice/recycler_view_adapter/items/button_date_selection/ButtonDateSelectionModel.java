package com.example.strile.sevice.recycler_view_adapter.items.button_date_selection;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

import java.util.Date;

public class ButtonDateSelectionModel extends BaseModel {

    private Date date;

    public ButtonDateSelectionModel(boolean topMargin) {
        super(topMargin);
        date = new Date(0);
    }

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
