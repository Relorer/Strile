package com.example.strile.sevice.recycler_view_adapter.models;

import java.util.Date;

public class DayModel extends BaseModel {

    private final Date date;
    private final boolean selected;

    public DayModel(boolean topMargin, Date date, boolean selected) {
        super(topMargin);
        this.date = date;
        this.selected = selected;
    }

    private DayModel(int id, boolean topMargin, Date date, boolean selected) {
        super(id, topMargin);
        this.date = date;
        this.selected = selected;
    }

    @Override
    public int getType() {
        return DAY_TYPE;
    }

    public Date getDate() {
        return date;
    }

    public boolean isSelected() {
        return selected;
    }

    public DayModel setState(boolean state) {
        return new DayModel(getId(), isTopMargin(), date, state);
    }
}
