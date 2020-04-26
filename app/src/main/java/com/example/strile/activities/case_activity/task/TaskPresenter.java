package com.example.strile.activities.case_activity.task;

import android.util.Log;
import android.view.View;

import com.example.strile.App;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.TaskModel;
import com.example.strile.database.models.TaskDatabaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;

import java.util.ArrayList;
import java.util.List;

public class TaskPresenter extends BaseCasePresenter<TaskDatabaseModel, TaskActivity> {

    private final TaskModel task;
    private ButtonAddSubtaskModel buttonAddSubtask = new ButtonAddSubtaskModel();
    private SeekBarDifficultModel seekBarDifficult = new SeekBarDifficultModel();
    private ButtonDateSelectionModel buttonDateSelection = new ButtonDateSelectionModel();
    private EditTextModel nameEditText = new EditTextModel(2, 80);
    private EditTextModel descriptionEditText = new EditTextModel(0, 0);

    public TaskPresenter(TaskModel task) {
        model = App.getInstance().getTaskModel();
        this.task = task;

        seekBarDifficult.setTopMargin(true);
        buttonDateSelection.setTopMargin(true);

        nameEditText.setHint("Name");
        nameEditText.setText(task.getName());
        descriptionEditText.setHint("Description");
        descriptionEditText.setText(task.getDescription());
        seekBarDifficult.setProgress(task.getDifficulty());
        buttonDateSelection.setDate(task.getDeadline());
    }

    @Override
    public void unbindView() {
        super.unbindView();
        if (task.getName().equals("")) task.setName("No name");
        updateCaseInDatabase(task);
    }

    @Override
    public void specialPurposeButtonClicked() {
        deleteCaseInDatabase(task);
        view().showSnackbar(view().getCaller(), "Task deleted", "Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCaseInDatabase(task);
            }
        });
        view().finish();
    }

    @Override
    public void backButtonClicked() {
        view().finish();
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