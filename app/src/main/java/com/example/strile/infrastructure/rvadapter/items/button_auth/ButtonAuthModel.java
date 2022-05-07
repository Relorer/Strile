package com.example.strile.infrastructure.rvadapter.items.button_auth;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class ButtonAuthModel extends BaseModel {
    private final String text;

    public ButtonAuthModel(boolean topMargin, String text) {
        super(topMargin);
        this.text = text;
    }

    public ButtonAuthModel(int id, boolean topMargin, String text) {
        super(id, topMargin);
        this.text = text;
    }

    @Override
    public int getType() {
        return BUTTON_AUTH;
    }

    public String getText() {
        return text;
    }
}
