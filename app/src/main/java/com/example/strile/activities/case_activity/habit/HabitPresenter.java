package com.example.strile.activities.case_activity.habit;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.service.date.Day;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.button_repeat.ButtonRepeatModel;
import com.example.strile.service.recycler_view_adapter.items.button_time_goal.ButtonTimeGoalModel;
import com.example.strile.service.recycler_view_adapter.items.current_streak.CurrentStreakModel;
import com.example.strile.service.recycler_view_adapter.items.edit_text.EditTextModel;
import com.example.strile.service.recycler_view_adapter.items.progress_bar_elapsed_time.ProgressBarElapsedTimeModel;
import com.example.strile.service.recycler_view_adapter.items.seek_bar_difficult.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        Habit habit = this.habit.getValue();
        assert habit != null;
        if (habit.getName().equals("")) habit.setName(view().getString(R.string.t_no_name));
        if (habit.getDaysRepeat() == 0) habit.setDaysRepeat(new boolean[]{
                true, true, true, true, true, true, true //7 days
        });
        repository.update(habit);
        super.unbindView();
    }

    @Override
    public void specialPurposeButtonClicked() {
        final Habit backupHabit = habit.getValue();
        repository.delete(backupHabit);
        view().showSnackbar(
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
        if (!habit.hasActiveObservers()) {
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
                    progressBarElapsedTime.setMax((int) (habit.getGoalTime() / 60 / 1000));
                    progressBarElapsedTime.setProgress((int) (habit.getElapsedTime() / 60 / 1000));
                    currentStreak.setStreak(habit.getStreakByDay(Day.getDateOfDayWithoutTime(new Date())));

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
    public void elapsedTimeClicked(ProgressBarElapsedTimeModel model) {
        view().openTimer(Objects.requireNonNull(habit.getValue()).getId());
    }
}