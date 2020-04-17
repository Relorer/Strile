package com.example.strile.activities.habit;

import com.example.strile.App;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.models.HabitModel;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.DifficultyManager;
import com.example.strile.sevice.presenter.BasePresenter;

public class HabitPresenter extends BasePresenter<HabitModel, HabitActivity> {

    private Habit habit;

    HabitPresenter(Habit habit) {
        model = App.getInstance().getHabitModel();
        this.habit = habit;
    }

    @Override
    protected void updateView() {
        view().setTextName(habit.getName());
        if (habit.getGoalTimeSeconds() != 0) {
            view().setVisibleViewTimeGoal(true);
            view().setTextTimeProgress(getTextProgress());
            view().setTextTimeProgressColor(DifficultyManager.getColor(habit.getDifficulty()));
            view().setProgressBarTimeMaximum(habit.getGoalTimeSeconds() / 60);
            view().setProgressBarTimeCurrentValue(habit.getElapsedTimeSeconds() / 60);
            view().setProgressBarTimeColor(DifficultyManager.getColor(habit.getDifficulty()));
        } else {
            view().setVisibleViewTimeGoal(false);
        }
        view().setTextStreak(String.valueOf(habit.getStreak()));
        view().setTextStreakColor(DifficultyManager.getColor(habit.getDifficulty()));
        changeTextRepeat();
        changeTextGoalTime();
    }

    void backButtonClicked() {
        view().finish();
    }

    private void changeTextRepeat() {
        view().setRepeatText(DateManager.fromArrayDaysToString(habit.getDaysRepeatAsArray()));
    }

    private void changeTextGoalTime() {
        int goalMinutes = habit.getGoalTimeSeconds() / 60;
        switch (goalMinutes) {
            case 0:
                view().setGoalTimeText("No time goal");
                break;
            case 60:
                view().setGoalTimeText("For 1 hour");
                break;
            default:
                view().setGoalTimeText("For " + goalMinutes + " minutes");
                break;
        }
    }

    private String getTextProgress() {
        int elapsed = habit.getElapsedTimeSeconds() / 60;
        int goal = habit.getGoalTimeSeconds() / 60;
        return elapsed + "/" + goal + " minutes";
    }
}
