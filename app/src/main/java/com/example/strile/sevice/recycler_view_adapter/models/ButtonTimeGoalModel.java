package com.example.strile.sevice.recycler_view_adapter.models;

public class ButtonTimeGoalModel extends BaseModel {

    private int goalTimeSeconds;

    @Override
    public int getType() {
        return BaseModel.BUTTON_TIME_GOAL_TYPE;
    }

    public int getGoalTimeSeconds() {
        return goalTimeSeconds;
    }

    public void setGoalTimeSeconds(int goalTimeSeconds) {
        this.goalTimeSeconds = goalTimeSeconds;
    }
}
