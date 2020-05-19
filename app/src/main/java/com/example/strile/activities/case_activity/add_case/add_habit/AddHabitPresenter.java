package com.example.strile.activities.case_activity.add_case.add_habit;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.button_repeat.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.items.button_time_goal.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.items.edit_text.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.items.seek_bar_difficult.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.List;

public class AddHabitPresenter extends BaseCasePresenter<AddHabitActivity> {

    private final Repository<Habit> repository;
    private final Habit habit;

    private final EditTextModel editTextName;
    private final ButtonRepeatModel buttonRepeat;
    private final ButtonTimeGoalModel buttonTimeGoal;
    private final SeekBarDifficultModel seekBarDifficult;

    public AddHabitPresenter() {
        repository = new HabitRepository(App.getInstance());
        habit = new Habit();

        editTextName = new EditTextModel(false, 1, 80);
        buttonRepeat = new ButtonRepeatModel(true, habit.getDaysRepeatAsArray());
        buttonTimeGoal = new ButtonTimeGoalModel(false, habit.getGoalTime());
        seekBarDifficult = new SeekBarDifficultModel(true, habit.getDifficulty());
    }

    @Override
    public void specialPurposeButtonClicked() {
        if (habit.getName().equals("")) {
            String message = view().getString(R.string.w_habit_name_empty);
            view().showToast(message);
        }
        else if (habit.getDaysRepeat() == 0) {
            String message = view().getString(R.string.w_habit_repeat_empty);
            view().showToast(message);
        }
        else {
            repository.insert(habit);
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
}