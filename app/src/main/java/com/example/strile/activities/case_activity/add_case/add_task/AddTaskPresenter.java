package com.example.strile.activities.case_activity.add_case.add_task;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Task;
import com.example.strile.database.repositories.Repository;
import com.example.strile.database.repositories.TaskRepository;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;
import com.example.strile.sevice.structures.Subtask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTaskPresenter extends BaseCasePresenter<AddTaskActivity> {

    private final Repository<Task> repository;
    private final Task task;

    private final EditTextModel editTextName;
    private final EditTextModel editTextDescription;
    private final ButtonDateSelectionModel buttonDateSelection;
    private final ButtonAddSubtaskModel buttonAddSubtask;
    private final SeekBarDifficultModel seekBarDifficult;

    private final List<SubtaskModel> subtaskModels;

    public AddTaskPresenter() {
        repository = new TaskRepository(App.getInstance());
        task = new Task();

        editTextName = new EditTextModel(false, 1, 80);
        editTextDescription = new EditTextModel(false, 0, 0);
        buttonDateSelection = new ButtonDateSelectionModel(true, new Date(0));
        buttonAddSubtask = new ButtonAddSubtaskModel(false);
        seekBarDifficult = new SeekBarDifficultModel(true, task.getDifficulty());

        subtaskModels = new ArrayList<>();
    }

    @Override
    public void specialPurposeButtonClicked() {
        if (task.getName().equals("")) {
            view().showToast(view().getString(R.string.w_task_name_empty));
        } else {
            for (SubtaskModel subtaskModel : subtaskModels) {
                task.getSubtasks().add(new Subtask(subtaskModel.getText(), subtaskModel.isComplete()));
            }
            repository.insert(task);
            view().finish();
        }
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    protected void updateView() {
        final List<BaseModel> models = new ArrayList<>();
        editTextName.setHint(view().getString(R.string.t_name));
        editTextDescription.setHint(view().getString(R.string.description));
        models.add(editTextName);
        models.add(editTextDescription);
        models.add(buttonDateSelection);
        models.addAll(subtaskModels);
        models.add(buttonAddSubtask);
        models.add(seekBarDifficult);
        view().setSortedList(models);
    }

    @Override
    public void editTextChanged(EditTextModel model) {
        if (model.equals(editTextName)) {
            task.setName(model.getText());
        } else if (model.equals(editTextDescription)) {
            task.setDescription(model.getText());
        }
    }

    @Override
    public void dateSelectionChanged(ButtonDateSelectionModel model) {
        task.setDeadline(model.getDate());
    }

    @Override
    public void addSubtaskButtonClicked(ButtonAddSubtaskModel model) {
        subtaskModels.add(new SubtaskModel(false, "", false));
        updateView();
    }

    @Override
    public void subtaskChanged(SubtaskModel model) {
        if (model.isDying()) {
            subtaskModels.remove(model);
            updateView();
        }
    }

    @Override
    public void difficultChanged(SeekBarDifficultModel model) {
        task.setDifficulty(model.getProgress());
    }
}
