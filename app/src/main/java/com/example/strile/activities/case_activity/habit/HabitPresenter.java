package com.example.strile.activities.case_activity.habit;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.sevice.Day;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.models.CurrentStreakModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitPresenter extends BaseCasePresenter<HabitActivity> {

    private final Repository<Habit> repository;
    private final LiveData<Habit> habit;

    private final EditTextModel editTextName;
    private final CurrentStreakModel currentStreak;
    private final ButtonRepeatModel buttonRepeat;
    private final ButtonTimeGoalModel buttonTimeGoal;
    private final SeekBarDifficultModel seekBarDifficult;

    private final int progressBarElapsedTimeId;

    public HabitPresenter(long habitId) {
        repository = new HabitRepository(App.getInstance());
        habit = repository.getById(habitId);

        editTextName = new EditTextModel(false, 1, 80);
        progressBarElapsedTimeId = new ProgressBarElapsedTimeModel(true).getId();
        currentStreak = new CurrentStreakModel(true);
        buttonRepeat = new ButtonRepeatModel(true);
        buttonTimeGoal = new ButtonTimeGoalModel(false);
        seekBarDifficult = new SeekBarDifficultModel(true);
    }

    @Override
    public void unbindView() {
        this.habit.removeObservers(view());
        Habit habit = this.habit.getValue();
        if (habit.getName().equals("")) habit.setName(view().getString(R.string.t_no_name));
        if (habit.getDaysRepeat() == 0) habit.setDaysRepeat(new boolean[] {
                true, true, true, true, true, true, true //7 days
        });
        repository.update(habit);
        super.unbindView();
    }

    @Override
    public void specialPurposeButtonClicked() {
        final Habit backupHabit = habit.getValue();
        repository.delete(backupHabit);
        view().showSnackbar(view().getCaller(),
                view().getString(R.string.w_habit_deleted),
                view().getString(R.string.undo), v -> repository.insert(backupHabit));
        view().finish();
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    protected void updateView() {
        habit.observe(view(), habit -> {
            if (habit != null && view() != null) {
                final List<BaseModel> models = new ArrayList<>();

                editTextName.setHint(view().getString(R.string.t_name));
                editTextName.setText(habit.getName());
                buttonRepeat.setDaysRepeat(habit.getDaysRepeatAsArray());
                buttonTimeGoal.setGoalTime(habit.getGoalTime());
                seekBarDifficult.setProgress(habit.getDifficulty());
                final ProgressBarElapsedTimeModel progressBarElapsedTime =
                        new ProgressBarElapsedTimeModel(progressBarElapsedTimeId, true);
                progressBarElapsedTime.setMax((int) (habit.getGoalTime() / 60 / 1000)); //todo careless cast: long to int
                progressBarElapsedTime.setProgress((int) (habit.getElapsedTime() / 60 / 1000));
                currentStreak.setStreak(habit.getStreakByDay(new Day(new Date()).getDateOfDayWithoutTime()));

                models.add(editTextName);
                if (habit.getGoalTime() > habit.getElapsedTime())
                    models.add(progressBarElapsedTime);
                models.add(currentStreak);
                models.add(buttonRepeat);
                models.add(buttonTimeGoal);
                models.add(seekBarDifficult);

                view().setSortedList(models);
            }
        });
    }

    @Override
    public void repeatChanged(ButtonRepeatModel model) {
        habit.getValue().setDaysRepeat(model.getDaysRepeat());
    }

    @Override
    public void timeGoalChanged(ButtonTimeGoalModel model) {
        habit.getValue().setGoalTime(model.getGoalTime());
        repository.update(habit.getValue());
    }

    @Override
    public void editTextChanged(EditTextModel model) {
        if (model.equals(editTextName))
            habit.getValue().setName(model.getText());
    }

    @Override
    public void difficultChanged(SeekBarDifficultModel model) {
        habit.getValue().setDifficulty(model.getProgress());
    }

    @Override
    public void elapsedTimeClicked(ProgressBarElapsedTimeModel model) {
        view().openTimer(habit.getValue().getId());
    }
}