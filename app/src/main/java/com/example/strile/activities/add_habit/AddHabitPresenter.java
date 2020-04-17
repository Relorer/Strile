package com.example.strile.activities.add_habit;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.models.HabitModel;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.DifficultyManager;
import com.example.strile.sevice.presenter.BasePresenter;

public class AddHabitPresenter extends BasePresenter<HabitModel, AddHabitActivity> {

    private Habit habit;

    AddHabitPresenter() {
        model = App.getInstance().getHabitModel();
        habit = new Habit();
        habit.setDaysRepeat(new boolean[] {true, true, true, true, true, true, true});
        habit.setDifficulty(DifficultyManager.maxDifficult / 3);
    }

    @Override
    protected void updateView() {
        changeTextGoalTime();
        view().setSeekBarDifficultMaximum(DifficultyManager.maxDifficult);
        view().setSeekBarDifficultProgress(habit.getDifficulty());
        changeTextDifficult();
        changeTextRepeat();
    }

    void doneButtonClicked() {
        if (habit.getName().equals("")) view().showToast("Habit's name shouldn't be empty!");
        else if (habit.getDaysRepeat() == 0) view().showToast("The habit should be repeated at least once a week!");
        else {
            addHabitInDatabase();
            view().finish();
        }
    }

    void backButtonClicked() {
        view().finish();
    }

    void repeatButtonClicked() {
        view().openDialogRepeatOptions(habit.getDaysRepeatAsArray());
    }

    void goalTimeButtonClicked() {
        view().openDialogTimeGoalOptions(habit.getGoalTimeSeconds());
    }

    void nameChanged(String name) {
        habit.setName(name.trim());
    }

    void goalTimeChanged(int time) {
        habit.setGoalTimeSeconds(time);
        changeTextGoalTime();
    }

    void repeatDaysChanged(boolean[] checkedDays) {
        habit.setDaysRepeat(checkedDays);
        changeTextRepeat();
    }

    void difficultChanged(int difficult) {
        habit.setDifficulty(difficult);
        changeTextDifficult();
    }

    private void addHabitInDatabase() {
        model.insertCase(habit, null);
    }

    private void changeTextRepeat() {
        view().setRepeatTextColor(R.color.colorBlack);
        if (habit.getDaysRepeat() == 0)
            view().setRepeatTextColor(R.color.colorRed);
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

    private void changeTextDifficult() {
        view().setTextDifficult(DifficultyManager.getName(habit.getDifficulty()));
        view().setTextDifficultColor(DifficultyManager.getColor(habit.getDifficulty()));
    }
}
