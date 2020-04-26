package com.example.strile.activities.case_activity.add_case.add_task;

import android.util.Log;

import com.example.strile.App;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.TaskModel;
import com.example.strile.database.models.TaskDatabaseModel;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.DifficultyManager;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddTaskPresenter extends BaseCasePresenter<TaskDatabaseModel, AddTaskActivity> {

    private TaskModel task;
    private ButtonAddSubtaskModel buttonAddSubtask = new ButtonAddSubtaskModel();
    private SeekBarDifficultModel seekBarDifficult = new SeekBarDifficultModel();
    private ButtonDateSelectionModel buttonDateSelection = new ButtonDateSelectionModel();
    private EditTextModel nameEditText = new EditTextModel(2, 80);
    private EditTextModel descriptionEditText = new EditTextModel(0, 0);

    AddTaskPresenter() {
        model = App.getInstance().getTaskModel();
        task = new TaskModel();
        task.setDeadline(0);
        task.setDifficulty(DifficultyManager.maxDifficult / 3);

        seekBarDifficult.setTopMargin(true);
        buttonDateSelection.setTopMargin(true);

        seekBarDifficult.setProgress(task.getDifficulty());
        nameEditText.setHint("Name");
        descriptionEditText.setHint("Description");
        buttonDateSelection.setDate(task.getDeadline());
    }

    @Override
    protected void updateView() {
        List<BaseModel> list = new ArrayList<BaseModel>();
        list.add(nameEditText);
        list.add(descriptionEditText);
        list.add(buttonDateSelection);
        list.addAll(task.getSubtasks());
        list.add(buttonAddSubtask);
        list.add(seekBarDifficult);
        view().setSortedList(list);
    }

    @Override
    public void specialPurposeButtonClicked() {
        if (task.getName().equals("")) {
            view().showToast("Task's name shouldn't be empty!");
        }
        else {
            task.setDateCreate(DateManager.getDateWithoutTime(Calendar.getInstance().getTimeInMillis()).getTime());
            addCaseInDatabase(task);
            view().finish();
        }
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    public void editTextChanged(EditTextModel model) {
        if (model.equals(nameEditText)) {
            task.setName(model.getText());
        } else if (model.equals(descriptionEditText)) {
            task.setDescription(model.getText());
        }
    }

    @Override
    public void dateSelectionChanged(ButtonDateSelectionModel model) {
        task.setDeadline(model.getDate());
    }

    @Override
    public void addSubtaskButtonClicked(ButtonAddSubtaskModel model) {
        task.getSubtasks().add(new SubtaskModel());
        updateView();
    }

    @Override
    public void subtaskChanged(SubtaskModel model) {
        if (model.isDying()) {
            task.getSubtasks().remove(model);
            updateView();
        }
    }

    @Override
    public void difficultChanged(SeekBarDifficultModel model) {
        task.setDifficulty(model.getProgress());
    }
}
