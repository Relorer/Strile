package com.example.strile.activities.case_activity;

import com.example.strile.service.presenter.BasePresenter;
import com.example.strile.service.recycler_view_adapter.items.button_add_subtask.ButtonAddSubtaskModel;
import com.example.strile.service.recycler_view_adapter.items.button_date_selection.ButtonDateSelectionModel;
import com.example.strile.service.recycler_view_adapter.items.button_repeat.ButtonRepeatModel;
import com.example.strile.service.recycler_view_adapter.items.button_time_goal.ButtonTimeGoalModel;
import com.example.strile.service.recycler_view_adapter.items.edit_text.EditTextModel;
import com.example.strile.service.recycler_view_adapter.items.progress_bar_elapsed_time.ProgressBarElapsedTimeModel;
import com.example.strile.service.recycler_view_adapter.items.seek_bar_difficult.SeekBarDifficultModel;
import com.example.strile.service.recycler_view_adapter.items.subtask.SubtaskModel;

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

    public void addSubtaskButtonClicked(ButtonAddSubtaskModel model) {
    }

    public void elapsedTimeClicked(ProgressBarElapsedTimeModel model) {
    }
}
