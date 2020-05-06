package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonAddSubtaskModel extends BaseModel {
    public ButtonAddSubtaskModel(boolean topMargin) {
        super(topMargin);
    }

    @Override
    public int getType() {
        return BUTTON_ADD_SUBTASK_TYPE;
    }
}
