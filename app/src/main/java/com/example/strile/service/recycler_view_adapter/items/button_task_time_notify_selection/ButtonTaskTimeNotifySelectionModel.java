package com.example.strile.service.recycler_view_adapter.items.button_task_time_notify_selection;

import com.example.strile.service.recycler_view_adapter.items.BaseModel;

public class ButtonTaskTimeNotifySelectionModel extends BaseModel {

    private long time;

    public ButtonTaskTimeNotifySelectionModel(boolean topMargin, long time) {
        super(topMargin);
        this.time = time;
    }

    public ButtonTaskTimeNotifySelectionModel(int id, boolean topMargin, long time) {
        super(id, topMargin);
        this.time = time;
    }

    @Override
    public int getType() {
        return BUTTON_TASK_TIME_NOTIFY_SELECTION_TYPE;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
