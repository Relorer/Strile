package com.example.strile.sevice.recycler_view_adapter.models;

import android.widget.EditText;

public class EditTextModel extends BaseModel {

    private String text = "";
    private String hint = "";
    private int lineCount;
    private int maxLength;

    public EditTextModel(int lineCount, int maxLength) {
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

    public void setText(String text) {
        this.text = text;
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

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
