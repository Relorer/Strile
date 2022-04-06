package com.example.strile.infrastructure.rvadapter.items.button_time_selection;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class ButtonTimeSelectionModel extends BaseModel {

    private long time;

    public ButtonTimeSelectionModel(boolean topMargin) {
        super(topMargin);
    }

    public ButtonTimeSelectionModel(boolean topMargin, long time) {
        super(topMargin);
        this.time = time;
    }

    public ButtonTimeSelectionModel(int id, boolean topMargin, long time) {
        super(id, topMargin);
        this.time = time;
    }

    @Override
    public int getType() {
        return BUTTON_TIME_SELECTION_TYPE;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
