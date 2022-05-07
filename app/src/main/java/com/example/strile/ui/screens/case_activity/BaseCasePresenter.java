package com.example.strile.ui.screens.case_activity;

import com.example.strile.infrastructure.presenter.BasePresenter;
import com.example.strile.infrastructure.rvadapter.items.button_date_selection.ButtonDateSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.button_repeat.ButtonRepeatModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_goal.ButtonTimeGoalModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_selection.ButtonTimeSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.edit_text.EditTextModel;
import com.example.strile.infrastructure.rvadapter.items.seek_bar_difficult.SeekBarDifficultModel;
import com.example.strile.infrastructure.rvadapter.items.subtask.SubtaskModel;

public abstract class BaseCasePresenter<V> extends BasePresenter<V> {
    public abstract void specialPurposeButtonClicked();

    public abstract void backButtonClicked();

    public void editTextChanged(EditTextModel model) {
    }

    public void subtaskChanged(SubtaskModel model) {
    }

    public void repeatChanged(ButtonRepeatModel model) {
    }

    public void timeGoalChanged(ButtonTimeGoalModel model) {
    }

    public void difficultChanged(SeekBarDifficultModel model) {
    }

    public void dateSelectionChanged(ButtonDateSelectionModel model) {
    }

    public void addSubtaskButtonClicked() {
    }

    public void elapsedTimeClicked() {
    }

    public void notifyTimeChanged(ButtonTimeSelectionModel model) {

    }
}
