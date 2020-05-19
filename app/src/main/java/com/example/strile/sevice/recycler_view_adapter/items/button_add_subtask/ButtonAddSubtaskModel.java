package com.example.strile.sevice.recycler_view_adapter.items.button_add_subtask;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class ButtonAddSubtaskModel extends BaseModel {
    public ButtonAddSubtaskModel(boolean topMargin) {
        super(topMargin);
    }

    @Override
    public int getType() {
        return BUTTON_ADD_SUBTASK_TYPE;
    }
}
