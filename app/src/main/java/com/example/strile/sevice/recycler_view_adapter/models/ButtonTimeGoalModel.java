package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonTimeGoalModel extends BaseModel {

    private final long goalTime;

    public ButtonTimeGoalModel(boolean topMargin, long goalTime) {
        super(topMargin);
        this.goalTime = goalTime;
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_TIME_GOAL_TYPE;
    }

    public long getGoalTime() {
        return goalTime;
    }
}
