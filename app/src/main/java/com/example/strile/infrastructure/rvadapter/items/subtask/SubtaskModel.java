package com.example.strile.infrastructure.rvadapter.items.subtask;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class SubtaskModel extends BaseModel {

    private String text;
    private boolean complete;
    private boolean dying;

    public SubtaskModel(boolean topMargin, String text, boolean complete) {
        super(topMargin);
        this.text = text;
        this.complete = complete;
        dying = false;
    }

    @Override
    public int getType() {
        return SUBTASK_TYPE;
    }

    public String getText() {
        return text;
    }

    public SubtaskModel setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isComplete() {
        return complete;
    }

    public SubtaskModel setState(boolean state) {
        complete = state;
        return this;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }
}
