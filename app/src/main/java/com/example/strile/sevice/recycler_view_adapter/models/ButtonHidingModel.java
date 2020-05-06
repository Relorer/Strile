package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonHidingModel extends BaseModel {

    private final boolean checked;
    private final int count;

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

    public ButtonHidingModel setState(boolean state) {
        return  new ButtonHidingModel(isTopMargin(), state, count);
    }
}
