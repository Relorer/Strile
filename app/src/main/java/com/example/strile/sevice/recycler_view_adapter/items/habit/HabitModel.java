package com.example.strile.sevice.recycler_view_adapter.items.habit;

import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.CaseModel;

import java.util.Date;

public class HabitModel extends BaseModel implements CaseModel {

    private final Habit habit;
    private final Date date;

    public HabitModel(boolean topMargin, Habit habit, Date date) {
        super(topMargin);
        this.habit = habit;
        this.date = date;
    }

    public HabitModel(int id, boolean topMargin, Habit habit, Date date) {
        super(id, topMargin);
        this.habit = habit;
        this.date = date;
    }

    @Override
    public int getType() {
        return HABIT_TYPE;
    }

    public String getName() {
        return habit.getName();
    }

    public boolean isGoalTime() {
        return habit.getGoalTime() != 0;
    }

    public int getStreak() {
        return habit.getStreakByDay(date);
    }

    public boolean isComplete() {
        return habit.isCompleteOnDay(date);
    }

    public HabitModel setState(boolean state) {
        habit.setStateForDay(state, date);
        return this;
    }

    public Habit getHabit() {
        return habit;
    }
}