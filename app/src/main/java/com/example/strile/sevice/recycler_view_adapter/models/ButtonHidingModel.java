package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonHidingModel extends BaseModel {
    private boolean checked;
    private String text;
    private int count;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getType()  {
        return BUTTON_HIDING_TYPE;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
