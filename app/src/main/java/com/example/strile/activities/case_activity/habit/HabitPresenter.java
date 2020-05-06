package com.example.strile.activities.case_activity.habit;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.models.CurrentStreakModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.List;

public class HabitPresenter extends BaseCasePresenter<HabitActivity> {
    @Override
    public void specialPurposeButtonClicked() {
        //todo
    }

    @Override
    public void backButtonClicked() {

    }

    @Override
    protected void updateView() {

    }


//    private Habit habit;
//
//    private EditTextModel editTextName = new EditTextModel(2, 80);
//    private ButtonRepeatModel buttonRepeat = new ButtonRepeatModel();
//    private ButtonTimeGoalModel buttonTimeGoal = new ButtonTimeGoalModel();
//    private SeekBarDifficultModel seekBarDifficult = new SeekBarDifficultModel();
//    private ProgressBarElapsedTimeModel progressBarElapsedTime = new ProgressBarElapsedTimeModel();
//    private CurrentStreakModel currentStreak = new CurrentStreakModel();
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public HabitPresenter(Habit habit) {
//        this.habit = habit;
//        model = App.getInstance().getHabitModel();
//
//        buttonRepeat.setTopMargin(true);
//        seekBarDifficult.setTopMargin(true);
//        progressBarElapsedTime.setTopMargin(true);
//        currentStreak.setTopMargin(true);
//
//        editTextName.setText(habit.getName());
//        buttonRepeat.setDaysRepeatArray(habit.getDaysRepeatAsArray());
//        buttonTimeGoal.setGoalTimeSeconds(habit.getGoalTimeSeconds());
//        seekBarDifficult.setProgress(habit.getDifficulty());
//        progressBarElapsedTime.setMax(habit.getGoalTimeSeconds() / 60);
//        progressBarElapsedTime.setProgress((int) (habit.getElapsedTimeSeconds() / 60));
//        currentStreak.setStreak(habit.getCurrentStreak());
//    }
//
//    @Override
//    public void unbindView() {
//        editTextName.setHint(view().getString(R.string.t_name));
//        super.unbindView();
//        if (habit.getName().equals("")) habit.setName(view().getString(R.string.t_no_name));
//        updateCaseInDatabase(habit);
//    }
//
//    @Override
//    protected void updateView() {
//        List<BaseModel> list = new ArrayList<>();
//        list.add(editTextName);
//        if (habit.getGoalTimeSeconds() != 0 && habit.getGoalTimeSeconds() != habit.getElapsedTimeSeconds())
//            list.add(progressBarElapsedTime);
//        list.add(currentStreak);
//        list.add(buttonRepeat);
//        list.add(buttonTimeGoal);
//        list.add(seekBarDifficult);
//        view().setSortedList(list);
//    }
//
//    @Override
//    public void specialPurposeButtonClicked() {
//        deleteCaseInDatabase(habit);
//        view().showSnackbar(view().getCaller(), view().getString(R.string.w_habit_deleted), view().getString(R.string.undo), v -> addCaseInDatabase(habit));
//        view().finish();
//    }
//
//    @Override
//    public void backButtonClicked() {
//        view().finish();
//    }
//
//    @Override
//    public void repeatChanged(ButtonRepeatModel model) {
//        habit.setDaysRepeat(model.getDaysRepeat());
//    }
//
//    @Override
//    public void timeGoalChanged(ButtonTimeGoalModel model) {
//        habit.setGoalTimeSeconds(model.getGoalTime());
//        progressBarElapsedTime.setMax(habit.getGoalTimeSeconds() / 60);
//        updateView();
//    }
//
//    @Override
//    public void editTextChanged(EditTextModel model) {
//        if (model.equals(editTextName))
//            habit.setName(model.getText());
//    }
//
//    @Override
//    public void difficultChanged(SeekBarDifficultModel model) {
//        habit.setDifficulty(model.getProgress());
//    }
//
//    @Override
//    public void elapsedTimeClicked(ProgressBarElapsedTimeModel model) {
//        view().openTimer(habit);
//    }
//
//    public void setHabit(Habit habit) {
//        this.habit = habit;
//        editTextName.setText(habit.getName());
//        buttonRepeat.setDaysRepeatArray(habit.getDaysRepeatAsArray());
//        buttonTimeGoal.setGoalTimeSeconds(habit.getGoalTimeSeconds());
//        seekBarDifficult.setProgress(habit.getDifficulty());
//        progressBarElapsedTime.setMax(habit.getGoalTimeSeconds() / 60);
//        progressBarElapsedTime.setProgress((int) (habit.getElapsedTimeSeconds() / 60));
//        currentStreak.setStreak(habit.getCurrentStreak());
//    }
}