package com.example.strile.sevice.recycler_view_adapter.models;

public class SubtaskModel extends BaseModel {

    private String text;
    private boolean complete;

    public SubtaskModel(boolean topMargin, String text, boolean complete) {
        super(topMargin);
        this.text = text;
        this.complete = complete;
    }

    @Override
    public int getType() {
        return SUBTASK_TYPE;
    }

    public String getText() {
        return text;
    }

    public boolean isComplete() {
        return complete;
    }

    public SubtaskModel setState(boolean state) {
        complete = state;
        return this;
    }

    public SubtaskModel setText(String text) {
        this.text = text;
        return this;
    }
}
