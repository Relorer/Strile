package com.example.strile.infrastructure.rvadapter.items.button_language_selection;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class ButtonLanguageSelectionModel extends BaseModel {
    protected ButtonLanguageSelectionModel(boolean topMargin) {
        super(topMargin);
    }

    protected ButtonLanguageSelectionModel(int id, boolean topMargin) {
        super(id, topMargin);
    }

    @Override
    public int getType() {
        return BUTTON_LANGUAGE_SELECTION_TYPE;
    }
}
