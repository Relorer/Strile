package com.example.strile.activities.case_activity.task;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Task;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;
import com.example.strile.sevice.structures.Subtask;

import java.util.ArrayList;
import java.util.List;

public class TaskPresenter extends BaseCasePresenter<TaskActivity> {
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

//    private final Task task;
//    private ButtonAddSubtaskModel buttonAddSubtask = new ButtonAddSubtaskModel();
//    private SeekBarDifficultModel seekBarDifficult = new SeekBarDifficultModel();
//    private ButtonDateSelectionModel buttonDateSelection = new ButtonDateSelectionModel();
//    private EditTextModel nameEditText = new EditTextModel(2, 80);
//    private EditTextModel descriptionEditText = new EditTextModel(0, 0);
//
//    private List<SubtaskModel> subtaskModels = new ArrayList<>();
//
//    public TaskPresenter(Task task) {
//        model = App.getInstance().getTaskModel();
//        this.task = task;
//
//        for (Subtask subtask : task.getSubtasks()) subtaskModels.add(new SubtaskModel(subtask.getName(), subtask.isComplete(), false));
//
//        seekBarDifficult.setTopMargin(true);
//        buttonDateSelection.setTopMargin(true);
//
//        nameEditText.setText(task.getName());
//        descriptionEditText.setText(task.getDescription());
//        seekBarDifficult.setProgress(task.getDifficulty());
//        buttonDateSelection.setDate(task.getDeadline());
//    }
//
//    @Override
//    public void unbindView() {
//        descriptionEditText.setHint(view().getString(R.string.description));
//        nameEditText.setHint(view().getString(R.string.t_name));
//        super.unbindView();
//        if (task.getName().equals("")) task.setName(view().getString(R.string.t_no_name));
//
//        task.getSubtasks().clear();
//        for (SubtaskModel subtaskModel : subtaskModels) {
//            task.getSubtasks().add(new Subtask(subtaskModel.getText(), subtaskModel.isComplete()));
//        }
//
//        updateCaseInDatabase(task);
//    }
//
//    @Override
//    public void specialPurposeButtonClicked() {
//        deleteCaseInDatabase(task);
//        view().showSnackbar(view().getCaller(), view().getString(R.string.w_task_deleted), view().getString(R.string.undo), v -> addCaseInDatabase(task));
//        view().finish();
//    }
//
//    @Override
//    public void backButtonClicked() {
//        view().finish();
//    }
//
//    @Override
//    protected void updateView() {
//        List<BaseModel> list = new ArrayList<BaseModel>();
//        list.add(nameEditText);
//        list.add(descriptionEditText);
//        list.add(buttonDateSelection);
//        list.addAll(subtaskModels);
//        list.add(buttonAddSubtask);
//        list.add(seekBarDifficult);
//        view().setSortedList(list);
//    }
//
//    @Override
//    public void editTextChanged(EditTextModel model) {
//        if (model.equals(nameEditText)) {
//            task.setName(model.getText());
//        } else if (model.equals(descriptionEditText)) {
//            task.setDescription(model.getText());
//        }
//    }
//
//    @Override
//    public void dateSelectionChanged(ButtonDateSelectionModel model) {
//        task.setDeadline(model.getDate());
//    }
//
//    @Override
//    public void addSubtaskButtonClicked(ButtonAddSubtaskModel model) {
//        subtaskModels.add(new SubtaskModel("", false, false));
//        updateView();
//    }
//
//    @Override
//    public void subtaskChanged(SubtaskModel model) {
//        if (model.isDying()) {
//            subtaskModels.remove(model);
//            updateView();
//        }
//    }
//
//    @Override
//    public void difficultChanged(SeekBarDifficultModel model) {
//        task.setDifficulty(model.getProgress());
//    }
}