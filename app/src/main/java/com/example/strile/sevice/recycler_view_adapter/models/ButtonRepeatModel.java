package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonRepeatModel extends BaseModel {

    private boolean[] daysRepeatArray;

    @Override
    public int getType() {
        return BUTTON_REPEAT_TYPE;
    }

    public boolean[] getDaysRepeatArray() {
        return daysRepeatArray;
    }

    public void setDaysRepeatArray(boolean[] daysRepeatArray) {
        this.daysRepeatArray = daysRepeatArray;
    }
}
