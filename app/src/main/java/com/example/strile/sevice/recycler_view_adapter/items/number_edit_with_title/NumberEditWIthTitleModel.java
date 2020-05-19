package com.example.strile.sevice.recycler_view_adapter.items.number_edit_with_title;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class NumberEditWIthTitleModel extends BaseModel {

    private final String title;
    private String postfix;
    private final int max;
    private final int min;
    private int number;

    public NumberEditWIthTitleModel(boolean topMargin, String title, String postfix, int max, int min, int number) {
        super(topMargin);
        this.title = title;
        this.postfix = postfix;
        this.max = max;
        this.min = min;
        this.number = number;
    }

    public NumberEditWIthTitleModel(int id, boolean topMargin, String title, String postfix, int max, int min, int number) {
        super(id, topMargin);
        this.title = title;
        this.postfix = postfix;
        this.max = max;
        this.min = min;
        this.number = number;
    }

    public String getPostfix() {
        return postfix;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public NumberEditWIthTitleModel setNumber(int number) {
        this.number = number;
        return this;
    }

    public NumberEditWIthTitleModel setPostfix(String postfix) {
        this.postfix = postfix;
        return this;
    }

    @Override
    public int getType() {
        return EDIT_NUMBER_TYPE;
    }
}
