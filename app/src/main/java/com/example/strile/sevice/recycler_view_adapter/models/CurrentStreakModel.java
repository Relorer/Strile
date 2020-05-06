package com.example.strile.sevice.recycler_view_adapter.models;

public class CurrentStreakModel extends BaseModel {

    private final int streak;

    public CurrentStreakModel(boolean topMargin, int streak) {
        super(topMargin);
        this.streak = streak;
    }

    @Override
    public int getType() {
        return BaseModel.CURRENT_STREAK_TYPE;
    }

    public int getStreak() {
        return streak;
    }
}
