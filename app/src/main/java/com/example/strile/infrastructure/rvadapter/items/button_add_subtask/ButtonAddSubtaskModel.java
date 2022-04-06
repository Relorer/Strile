package com.example.strile.infrastructure.rvadapter.items.button_add_subtask;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class ButtonAddSubtaskModel extends BaseModel {
    public ButtonAddSubtaskModel(boolean topMargin) {
        super(topMargin);
    }

    @Override
    public int getType() {
        return BUTTON_ADD_SUBTASK_TYPE;
    }
}
