package com.example.strile.sevice.recycler_view_adapter.models;

import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;

public class DayModel extends BaseModel {

    private long day;
    private boolean selected;

    public DayModel(long day, boolean selected) {
        this.day = day;
        this.selected = selected;
    }

    @Override
    public int getType() {
        return DAY_TYPE;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyOfChanges();
    }
}
