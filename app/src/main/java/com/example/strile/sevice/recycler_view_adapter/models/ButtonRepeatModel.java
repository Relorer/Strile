package com.example.strile.sevice.recycler_view_adapter.models;

import androidx.annotation.NonNull;

public class ButtonRepeatModel extends BaseModel {

    private final boolean[] daysRepeat;

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
}
