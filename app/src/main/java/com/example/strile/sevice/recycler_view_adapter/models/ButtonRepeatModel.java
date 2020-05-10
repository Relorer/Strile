package com.example.strile.sevice.recycler_view_adapter.models;

import androidx.annotation.NonNull;

public class ButtonRepeatModel extends BaseModel {

    private boolean[] daysRepeat;

    public ButtonRepeatModel(boolean topMargin, @NonNull boolean[] daysRepeat) {
        super(topMargin);
        this.daysRepeat = daysRepeat;
    }

    @Override
    public int getType() {
        return BUTTON_REPEAT_TYPE;
    }

    public boolean[] getDaysRepeat() {
        return daysRepeat;
    }

    public ButtonRepeatModel setDaysRepeat(boolean[] daysRepeat) {
        this.daysRepeat = daysRepeat;
        return this;
    }
}
