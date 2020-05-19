package com.example.strile.sevice.recycler_view_adapter.items.edit_text;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class EditTextModel extends BaseModel {

    private final int lineCount;
    private final int maxLength;
    private String text = "";
    private String hint = "";

    public EditTextModel(boolean topMargin, int lineCount, int maxLength) {
        super(topMargin);
        this.lineCount = lineCount;
        this.maxLength = maxLength;
    }

    @Override
    public int getType() {
        return EDIT_TEXT_TYPE;
    }

    public String getText() {
        return text;
    }

    public String getHint() {
        return hint;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public EditTextModel setText(String text) {
        this.text = text;
        return this;
    }

    public EditTextModel setHint(String hint) {
        this.hint = hint;
        return this;
    }
}
