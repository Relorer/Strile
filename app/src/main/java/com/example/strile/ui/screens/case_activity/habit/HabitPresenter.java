package com.example.strile.ui.screens.case_activity.habit;

import androidx.lifecycle.LiveData;

import com.example.strile.R;
import com.example.strile.ui.screens.case_activity.BaseCasePresenter;
import com.example.strile.data_firebase.models.Habit;
import com.example.strile.data_firebase.repositories.HabitRepository;
import com.example.strile.utilities.extensions.DateUtilities;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_repeat.ButtonRepeatModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_goal.ButtonTimeGoalModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_selection.ButtonTimeSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.current_streak.CurrentStreakModel;
import com.example.strile.infrastructure.rvadapter.items.edit_text.EditTextModel;
import com.example.strile.infrastructure.rvadapter.items.progress_bar_elapsed_time.ProgressBarElapsedTimeModel;
import com.example.strile.infrastructure.rvadapter.items.seek_bar_difficult.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HabitPresenter extends BaseCasePresenter<HabitActivity> {

    private final HabitRepository repository;
    private final LiveData<Habit> habit;

    private final EditTextModel editTextName;
    private final CurrentStreakModel currentStreak;
    private final ButtonRepeatModel buttonRepeat;
    private final ButtonTimeGoalModel buttonTimeGoal;
    private final SeekBarDifficultModel seekBarDifficult;
    private final ButtonTimeSelectionModel buttonTimeSelection;

    private final int progressBarElapsedTimeId;

    private boolean isDeleted = false;

    private Habit backupHabit;

    public HabitPresenter(String habitId) {
        repository = new HabitRepository();
        habit = repository.getById(habitId);

        editTextName = new EditTextModel(false, 1, 80);
        progressBarElapsedTimeId = new ProgressBarElapsedTimeModel(true).getId();
        currentStreak = new CurrentStreakModel(true);
        buttonRepeat = new ButtonRepeatModel(true);
        buttonTimeGoal = new ButtonTimeGoalModel(false);
        buttonTimeSelection = new ButtonTimeSelectionModel(false);
        seekBarDifficult = new SeekBarDifficultModel(true);
    }

    @Override
    public void unbindView() {
        if (!isDeleted) {
            Habit habit = this.habit.getValue();
            if (habit != null) {
                if (habit.getName().equals("")) habit.setName(view().getString(R.string.t_no_name));
                if (habit.getDaysRepeat() == 0) habit.setDaysRepeat(new boolean[]{
                        true, true, true, true, true, true, true //7 days
                });
                repository.update(habit);
            }
        }
        super.unbindView();
    }

    @Override
    public void specialPurposeButtonClicked() {
        backupHabit = habit.getValue();
        repository.delete(Objects.requireNonNull(backupHabit));
        isDeleted = true;
        view().showSnackbar(
                view().getString(R.string.w_habit_deleted),
                view().getString(R.string.undo), v -> repository.update(backupHabit));
        view().finish();
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    protected void updateView() {
        if (!habit.hasActiveObservers()) {
            habit.observe(view(), habit -> {
                if (habit != null && view() != null) {
                    backupHabit = habit;
                    final List<BaseModel> models = new ArrayList<>();

                    editTextName.setHint(view().getString(R.string.t_name));
                    editTextName.setText(habit.getName());
                    buttonRepeat.setDaysRepeat(habit.getDaysRepeatAsArray());
                    buttonTimeGoal.setGoalTime(habit.getGoalTime());
                    seekBarDifficult.setProgress(habit.getDifficulty());
                    final ProgressBarElapsedTimeModel progressBarElapsedTime =
                            new ProgressBarElapsedTimeModel(progressBarElapsedTimeId, true);
                    progressBarElapsedTime.setMax((int) (habit.getGoalTime() / 60 / 1000));
                    progressBarElapsedTime.setProgress((int) (habit.getElapsedTime() / 60 / 1000));
                    currentStreak.setStreak(habit.streakByDay(DateUtilities.getDateOfDayWithoutTime(new Date())));

                    models.add(editTextName);
                    if (habit.getGoalTime() > habit.getElapsedTime())
                        models.add(progressBarElapsedTime);
                    models.add(currentStreak);
                    models.add(buttonRepeat);
                    models.add(buttonTimeGoal);
                    buttonTimeSelection.setTime(habit.getNotificationTime());
                    models.add(buttonTimeSelection);
                    models.add(seekBarDifficult);

                    view().setSortedList(models);
                }
                else if (habit == null) {
                    if (backupHabit != null) {
                        view().showSnackbar(
                                view().getString(R.string.w_habit_deleted),
                                view().getString(R.string.undo), v -> repository.update(backupHabit));
                    }
                    view().finish();
                }
            });
        }
    }

    @Override
    public void repeatChanged(ButtonRepeatModel model) {
        Objects.requireNonNull(habit.getValue()).setDaysRepeat(model.getDaysRepeat());
    }

    @Override
    public void timeGoalChanged(ButtonTimeGoalModel model) {
        Objects.requireNonNull(habit.getValue()).setGoalTime(model.getGoalTime());
        repository.update(habit.getValue());
    }

    @Override
    public void editTextChanged(EditTextModel model) {
        if (model.equals(editTextName))
            Objects.requireNonNull(habit.getValue()).setName(model.getText());
    }

    @Override
    public void difficultChanged(SeekBarDifficultModel model) {
        Objects.requireNonNull(habit.getValue()).setDifficulty(model.getProgress());
    }

    @Override
    public void elapsedTimeClicked() {
        view().openTimer(Objects.requireNonNull(habit.getValue()).getId());
    }

    @Override
    public void notifyTimeChanged(ButtonTimeSelectionModel model) {
        Objects.requireNonNull(habit.getValue()).setNotificationTime(model.getTime());
    }
}