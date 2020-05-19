package com.example.strile.sevice.recycler_view_adapter.items.button_repeat;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class ButtonRepeatModel extends BaseModel {

    private boolean[] daysRepeat;

    public ButtonRepeatModel(boolean topMargin) {
        super(topMargin);
        daysRepeat = new boolean[7];
    }

    public ButtonRepeatModel(boolean topMargin, boolean[] daysRepeat) {
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
