package com.example.strile.service.recycler_view_adapter.items.button_language_selection;

import com.example.strile.service.recycler_view_adapter.items.BaseModel;

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
