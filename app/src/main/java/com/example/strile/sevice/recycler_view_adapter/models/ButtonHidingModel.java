package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonHidingModel extends BaseModel {

    private boolean checked;
    private int count;

    public ButtonHidingModel(boolean topMargin, boolean checked, int count) {
        super(topMargin);
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

    public ButtonHidingModel setCount(int count) {
        this.count = count;
        return this;
    }

    public ButtonHidingModel setState(boolean state) {
        checked = state;
        return this;
    }
}
