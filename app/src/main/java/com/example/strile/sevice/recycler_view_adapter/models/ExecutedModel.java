package com.example.strile.sevice.recycler_view_adapter.models;

public class ExecutedModel extends BaseModel {

    private final String name;
    private final int experience;

    public ExecutedModel(boolean topMargin, String name, int experience) {
        super(topMargin);
        this.name = name;
        this.experience = experience;
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
}
