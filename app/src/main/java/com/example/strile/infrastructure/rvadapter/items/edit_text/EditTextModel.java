package com.example.strile.infrastructure.rvadapter.items.edit_text;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

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

    public EditTextModel setText(String text) {
        this.text = text;
        return this;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
