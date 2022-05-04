package com.example.strile.ui.screens.case_activity.add_case.add_habit;

import com.example.strile.R;
import com.example.strile.ui.screens.case_activity.BaseCasePresenter;
import com.example.strile.data_firebase.models.Habit;
import com.example.strile.data_firebase.repositories.HabitRepository;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_repeat.ButtonRepeatModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_goal.ButtonTimeGoalModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_selection.ButtonTimeSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.edit_text.EditTextModel;
import com.example.strile.infrastructure.rvadapter.items.seek_bar_difficult.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.List;

public class AddHabitPresenter extends BaseCasePresenter<AddHabitActivity> {

    private final HabitRepository repository;
    private final Habit habit;

    private final EditTextModel editTextName;
    private final ButtonRepeatModel buttonRepeat;
    private final ButtonTimeGoalModel buttonTimeGoal;
    private final ButtonTimeSelectionModel buttonTimeSelection;
    private final SeekBarDifficultModel seekBarDifficult;

    public AddHabitPresenter() {
        repository = new HabitRepository();
        habit = new Habit();

        editTextName = new EditTextModel(false, 1, 80);
        buttonRepeat = new ButtonRepeatModel(true, habit.getDaysRepeatAsArray());
        buttonTimeGoal = new ButtonTimeGoalModel(false, habit.getGoalTime());
        buttonTimeSelection = new ButtonTimeSelectionModel(false, habit.getNotificationTime());
        seekBarDifficult = new SeekBarDifficultModel(true, habit.getDifficulty());
    }

    @Override
    public void specialPurposeButtonClicked() {
        if (habit.getName().equals("")) {
            String message = view().getString(R.string.w_habit_name_empty);
            view().showToast(message);
        } else if (habit.getDaysRepeat() == 0) {
            String message = view().getString(R.string.w_habit_repeat_empty);
            view().showToast(message);
        } else {
            repository.update(habit);
            view().finish();
        }
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    protected void updateView() {
        List<BaseModel> models = new ArrayList<>();
        editTextName.setHint(view().getString(R.string.t_name));
        models.add(editTextName);
        models.add(buttonRepeat);
        models.add(buttonTimeGoal);
        models.add(buttonTimeSelection);
        models.add(seekBarDifficult);
        view().setSortedList(models);
    }

    @Override
    public void repeatChanged(ButtonRepeatModel model) {
        habit.setDaysRepeat(model.getDaysRepeat());
    }

    @Override
    public void timeGoalChanged(ButtonTimeGoalModel model) {
        habit.setGoalTime(model.getGoalTime());
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

    @Override
    public void notifyTimeChanged(ButtonTimeSelectionModel model) {
        habit.setNotificationTime(model.getTime());
    }
}