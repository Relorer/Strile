package com.example.strile.sevice.recycler_view_adapter.items.executed;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class ExecutedModel extends BaseModel {

    private final String name;
    private final int experience;
    private final long dateCreate;

    public ExecutedModel(int id, boolean topMargin, String name, int experience, long dateCreate) {
        super(id, topMargin);
        this.name = name;
        this.experience = experience;
        this.dateCreate = dateCreate;
    }

    public ExecutedModel(boolean topMargin, String name, int experience, long dateCreate) {
        super(topMargin);
        this.name = name;
        this.experience = experience;
        this.dateCreate = dateCreate;
    }

    @Override
    public int getType() {
        return EXECUTED_TYPE;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public long getDateCreate() {
        return dateCreate;
    }
}
