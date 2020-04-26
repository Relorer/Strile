package com.example.strile.sevice.recycler_view_adapter.models;

public class CurrentStreakModel extends BaseModel {

    private int streak;

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
