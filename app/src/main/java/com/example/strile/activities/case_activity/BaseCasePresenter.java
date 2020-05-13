package com.example.strile.activities.case_activity;

import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;

public abstract class BaseCasePresenter<V> extends BasePresenter<V>{
    public abstract void specialPurposeButtonClicked();
    public abstract void backButtonClicked();

    public void editTextChanged(EditTextModel model) {}
    public void subtaskChanged(SubtaskModel model) {}
    public void repeatChanged(ButtonRepeatModel model) {}
    public void timeGoalChanged(ButtonTimeGoalModel model) {}
    public void difficultChanged(SeekBarDifficultModel model) {}
    public void dateSelectionChanged(ButtonDateSelectionModel model) {}
    public void addSubtaskButtonClicked(ButtonAddSubtaskModel model) {}
    public void elapsedTimeClicked(ProgressBarElapsedTimeModel model) {}
}
