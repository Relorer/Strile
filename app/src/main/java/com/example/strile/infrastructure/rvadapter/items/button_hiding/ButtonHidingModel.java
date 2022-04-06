package com.example.strile.infrastructure.rvadapter.items.button_hiding;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class ButtonHidingModel extends BaseModel {

    private final int count;
    private boolean checked;

    public ButtonHidingModel(boolean topMargin, boolean checked, int count) {
        super(topMargin);
        this.checked = checked;
        this.count = count;
    }

    public ButtonHidingModel(int id, boolean topMargin, boolean checked, int count) {
        super(id, topMargin);
        this.checked = checked;
        this.count = count;
    }

    @Override
    public int getType() {
        return BUTTON_HIDING_TYPE;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getCount() {
        return count;
    }

    public ButtonHidingModel setState(boolean state) {
        checked = state;
        return this;
    }
}
