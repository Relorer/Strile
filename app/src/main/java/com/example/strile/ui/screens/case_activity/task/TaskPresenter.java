package com.example.strile.ui.screens.case_activity.task;

import androidx.lifecycle.LiveData;

import com.example.strile.R;
import com.example.strile.data_firebase.models.Subtask;
import com.example.strile.data_firebase.repositories.TaskRepository;
import com.example.strile.ui.screens.case_activity.BaseCasePresenter;
import com.example.strile.data_firebase.models.Task;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_add_subtask.ButtonAddSubtaskModel;
import com.example.strile.infrastructure.rvadapter.items.button_date_selection.ButtonDateSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.edit_text.EditTextModel;
import com.example.strile.infrastructure.rvadapter.items.seek_bar_difficult.SeekBarDifficultModel;
import com.example.strile.infrastructure.rvadapter.items.subtask.SubtaskModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskPresenter extends BaseCasePresenter<TaskActivity> {

    private final TaskRepository repository;
    private final LiveData<Task> task;

    private final EditTextModel editTextName;
    private final EditTextModel editTextDescription;
    private final ButtonDateSelectionModel buttonDateSelection;
    private final ButtonAddSubtaskModel buttonAddSubtask;
    private final SeekBarDifficultModel seekBarDifficult;

    private final List<SubtaskModel> subtaskModels;

    private boolean isDeleted = false;

    private Task backupTask;

    public TaskPresenter(String taskId) {
        repository = new TaskRepository();
        task = repository.getById(taskId);

        editTextName = new EditTextModel(false, 1, 80);
        editTextDescription = new EditTextModel(false, 0, 0);
        buttonDateSelection = new ButtonDateSelectionModel(true);
        buttonAddSubtask = new ButtonAddSubtaskModel(false);
        seekBarDifficult = new SeekBarDifficultModel(true);

        subtaskModels = new ArrayList<>();
    }

    @Override
    public void unbindView() {
        if (!isDeleted) {
            Task task = this.task.getValue();
            if (task != null) {
                if (task.getName().equals("")) task.setName(view().getString(R.string.t_no_name));
                task.getSubtasks().clear();
                task.getSubtasks().addAll(subtaskModels.stream()
                        .map(m -> new Subtask(m.getText(), m.isComplete()))
                        .collect(Collectors.toList()));
                repository.update(task);
            }
        }
        super.unbindView();
    }

    @Override
    public void specialPurposeButtonClicked() {
        backupTask = task.getValue();
        repository.delete(Objects.requireNonNull(backupTask));
        isDeleted = true;
        view().showSnackbar(
                view().getString(R.string.w_task_deleted),
                view().getString(R.string.undo), v -> repository.update(backupTask));
        view().finish();
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    protected void updateView() {
        if (!task.hasActiveObservers()) {
            task.observe(view(), this::_updateView);
        }
    }

    protected void _updateView(Task task) {
        if (task != null && view() != null) {
            backupTask = task;
            if (task.getSubtasks().size() > 0 && subtaskModels.size() == 0) {
                subtaskModels.addAll(task.getSubtasks().stream()
                        .map(m -> new SubtaskModel(false, m.getName(), m.isComplete()))
                        .collect(Collectors.toList()));
                task.getSubtasks().clear();
            }

            final List<BaseModel> models = new ArrayList<>();

            editTextName.setHint(view().getString(R.string.t_name));
            editTextName.setText(task.getName());
            editTextDescription.setHint(view().getString(R.string.description));
            editTextDescription.setText(task.getDescription());
            buttonDateSelection.setDate(new Date(task.getDeadline()));
            seekBarDifficult.setProgress(task.getDifficulty());

            models.add(editTextName);
            models.add(editTextDescription);
            models.add(buttonDateSelection);
            models.addAll(subtaskModels);
            models.add(buttonAddSubtask);
            models.add(seekBarDifficult);
            view().setSortedList(models);
        } else if (task == null) {
            if (backupTask != null) {
                view().showSnackbar(
                        view().getString(R.string.w_task_deleted),
                        view().getString(R.string.undo), v -> repository.update(backupTask));
            }
            view().finish();
        }
    }

    @Override
    public void editTextChanged(EditTextModel model) {
        final Task task = this.task.getValue();
        assert task != null;
        if (model.equals(editTextName)) {
            task.setName(model.getText());
        } else if (model.equals(editTextDescription)) {
            task.setDescription(model.getText());
        }
    }

    @Override
    public void dateSelectionChanged(ButtonDateSelectionModel model) {
        final Task task = this.task.getValue();
        assert task != null;
        task.setDeadlineFromDate(model.getDate());
    }

    @Override
    public void addSubtaskButtonClicked() {
        subtaskModels.add(new SubtaskModel(false, "", false));
        _updateView(this.task.getValue());
    }

    @Override
    public void subtaskChanged(SubtaskModel model) {
        if (model.isDying()) {
            subtaskModels.remove(model);
            _updateView(this.task.getValue());
        }
    }

    @Override
    public void difficultChanged(SeekBarDifficultModel model) {
        final Task task = this.task.getValue();
        assert task != null;
        task.setDifficulty(model.getProgress());
    }

}