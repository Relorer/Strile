package com.example.strile.infrastructure.rvadapter.items.button_time_goal;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public class ButtonTimeGoalModel extends BaseModel {

    private long goalTime;

    public ButtonTimeGoalModel(boolean topMargin) {
        super(topMargin);
        this.goalTime = 0;
    }

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

    public ButtonTimeGoalModel setGoalTime(long goalTime) {
        this.goalTime = goalTime;
        return this;
    }
}
