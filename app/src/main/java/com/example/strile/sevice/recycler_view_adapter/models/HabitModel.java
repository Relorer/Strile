package com.example.strile.sevice.recycler_view_adapter.models;

public class HabitModel extends BaseModel implements CaseModel {

    private final String name;
    private final boolean goalTime;
    private final int streak;
    private final boolean complete;

    public HabitModel(boolean topMargin, String name, boolean goalTime, int streak, boolean complete) {
        super(topMargin);
        this.name = name;
        this.goalTime = goalTime;
        this.streak = streak;
        this.complete = complete;
    }

    @Override
    public int getType() {
        return HABIT_TYPE;
    }

    public String getName() {
        return name;
    }

    public boolean isGoalTime() {
        return goalTime;
    }

    public int getStreak() {
        return streak;
    }

    public boolean isComplete() {
        return complete;
    }

    public HabitModel setState(boolean state) {
        return new HabitModel(isTopMargin(), name, goalTime, streak, state);
    }
}
