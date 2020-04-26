package com.example.strile.activities.case_activity.habit;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.strile.App;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.database.models.HabitDatabaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.models.CurrentStreakModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.List;

public class HabitPresenter extends BaseCasePresenter<HabitDatabaseModel, HabitActivity> {

    private final HabitModel habit;

    private EditTextModel editTextName = new EditTextModel(2, 80);
    private ButtonRepeatModel buttonRepeat = new ButtonRepeatModel();
    private ButtonTimeGoalModel buttonTimeGoal = new ButtonTimeGoalModel();
    private SeekBarDifficultModel seekBarDifficult = new SeekBarDifficultModel();
    private ProgressBarElapsedTimeModel progressBarElapsedTime = new ProgressBarElapsedTimeModel();
    private CurrentStreakModel currentStreak = new CurrentStreakModel();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public HabitPresenter(HabitModel habit) {
        this.habit = habit;
        model = App.getInstance().getHabitModel();

        buttonRepeat.setTopMargin(true);
        seekBarDifficult.setTopMargin(true);
        progressBarElapsedTime.setTopMargin(true);
        currentStreak.setTopMargin(true);

        editTextName.setText(habit.getName());
        editTextName.setHint("Name");
        buttonRepeat.setDaysRepeatArray(habit.getDaysRepeatAsArray());
        buttonTimeGoal.setGoalTimeSeconds(habit.getGoalTimeSeconds());
        seekBarDifficult.setProgress(habit.getDifficulty());
        progressBarElapsedTime.setMax(habit.getGoalTimeSeconds() / 60);
        progressBarElapsedTime.setProgress(habit.getElapsedTimeSeconds());
        currentStreak.setStreak(habit.getCurrentStreak());
    }

    @Override
    public void unbindView() {
        super.unbindView();
        if (habit.getName().equals("")) habit.setName("No name");
        updateCaseInDatabase(habit);
    }

    @Override
    protected void updateView() {
        List<BaseModel> list = new ArrayList<>();
        list.add(editTextName);
        if (habit.getGoalTimeSeconds() != 0)
            list.add(progressBarElapsedTime);
        list.add(currentStreak);
        list.add(buttonRepeat);
        list.add(buttonTimeGoal);
        list.add(seekBarDifficult);
        view().setSortedList(list);
    }

    @Override
    public void specialPurposeButtonClicked() {
        deleteCaseInDatabase(habit);
        view().showSnackbar(view().getCaller(), "Habit deleted", "Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCaseInDatabase(habit);
            }
        });
        view().finish();
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    public void repeatChanged(ButtonRepeatModel model) {
        habit.setDaysRepeat(model.getDaysRepeatArray());
    }

    @Override
    public void timeGoalChanged(ButtonTimeGoalModel model) {
        habit.setGoalTimeSeconds(model.getGoalTimeSeconds());
        progressBarElapsedTime.setMax(habit.getGoalTimeSeconds() / 60);
        updateView();
    }

    @Override
    public void editTextChanged(EditTextModel model) {
        if (model.equals(editTextName))
            habit.setName(model.getText());
    }

    @Override
    public void difficultChanged(SeekBarDifficultModel model) {
        habit.setDifficulty(model.getProgress());
    }
}