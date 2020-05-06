package com.example.strile.activities.case_activity.add_case.add_habit;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.Difficulty;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

import java.util.ArrayList;
import java.util.List;

public class AddHabitPresenter extends BaseCasePresenter<AddHabitActivity> {
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
//
//    AddHabitPresenter() {
//        model = App.getInstance().getHabitModel();
//
//        habit = new Habit();
//        habit.setDaysRepeat(new boolean[]{true, true, true, true, true, true, true});
//        habit.setDifficulty(Difficulty.maxDifficulty / 3);
//
//        buttonRepeat.setTopMargin(true);
//        seekBarDifficult.setTopMargin(true);
//
//        buttonRepeat.setDaysRepeatArray(habit.getDaysRepeatAsArray());
//        buttonTimeGoal.setGoalTimeSeconds(habit.getGoalTimeSeconds());
//        seekBarDifficult.setProgress(habit.getDifficulty());
//    }
//
//    @Override
//    protected void updateView() {
//        editTextName.setHint(view().getString(R.string.t_name));
//        List<BaseModel> list = new ArrayList<>();
//        list.add(editTextName);
//        list.add(buttonRepeat);
//        list.add(buttonTimeGoal);
//        list.add(seekBarDifficult);
//        view().setSortedList(list);
//    }
//
//    @Override
//    public void specialPurposeButtonClicked() {
//        if (habit.getName().equals("")) view().showToast(view().getString(R.string.w_habit_name_empty));
//        else if (habit.getDaysRepeat() == 0)
//            view().showToast(view().getString(R.string.w_habit_repeat_empty));
//        else {
//            addCaseInDatabase(habit);
//            view().finish();
//        }
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
}
