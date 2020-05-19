package com.example.strile.sevice.recycler_view_adapter.items.current_streak;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class CurrentStreakModel extends BaseModel {

    private int streak;

    public CurrentStreakModel(boolean topMargin) {
        super(topMargin);
    }

    @Override
    public int getType() {
        return BaseModel.CURRENT_STREAK_TYPE;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}
