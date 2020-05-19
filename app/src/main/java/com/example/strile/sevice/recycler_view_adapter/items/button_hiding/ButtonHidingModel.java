package com.example.strile.sevice.recycler_view_adapter.items.button_hiding;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class ButtonHidingModel extends BaseModel {

    private boolean checked;
    private final int count;

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
    public int getType()  {
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
