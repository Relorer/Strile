package com.example.strile.sevice.recycler_view_adapter.models;

public class SubtaskModel extends BaseModel {

    private String text = "";
    private boolean complete = false;
    private boolean dying = false;

    public SubtaskModel(String text, boolean complete, boolean dying) {
        this.text = text;
        this.complete = complete;
        this.dying = dying;
    }

    @Override
    public int getType() {
        return SUBTASK_TYPE;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }
}
