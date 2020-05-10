package com.example.strile.sevice.recycler_view_adapter.models;

public class EditTextModel extends BaseModel {

    private final String hint;
    private final int lineCount;
    private final int maxLength;
    private String text;

    public EditTextModel(boolean topMargin, String text, String hint, int lineCount, int maxLength) {
        super(topMargin);
        this.text = text;
        this.hint = hint;
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
}
