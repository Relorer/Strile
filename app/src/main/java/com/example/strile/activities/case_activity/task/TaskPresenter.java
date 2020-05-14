package com.example.strile.activities.case_activity.task;

import androidx.lifecycle.LiveData;

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
import java.util.stream.Collectors;

public class TaskPresenter extends BaseCasePresenter<TaskActivity> {

    private final Repository<Task> repository;
    private final LiveData<Task> task;

    private final EditTextModel editTextName;
    private final EditTextModel editTextDescription;
    private final ButtonDateSelectionModel buttonDateSelection;
    private final ButtonAddSubtaskModel buttonAddSubtask;
    private final SeekBarDifficultModel seekBarDifficult;

    private final List<SubtaskModel> subtaskModels;

    public TaskPresenter(long taskId) {
        repository = new TaskRepository(App.getInstance());
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
        this.task.removeObservers(view());
        Task task = this.task.getValue();
        if (task.getName().equals("")) task.setName(view().getString(R.string.t_no_name));
        task.getSubtasks().addAll(subtaskModels.stream()
                .map(m -> new Subtask(m.getText(), m.isComplete()))
                .collect(Collectors.toList()));
        repository.update(task);
        super.unbindView();
    }

    @Override
    public void specialPurposeButtonClicked() {
        final Task backupTask = task.getValue();
        repository.delete(backupTask);
        view().showSnackbar(view().getCaller(),
                view().getString(R.string.w_task_deleted),
                view().getString(R.string.undo), v -> repository.insert(backupTask));
        view().finish();
    }

    @Override
    public void backButtonClicked() {
        view().finish();
    }

    @Override
    protected void updateView() {
        task.observe(view(), task -> {
            if (task != null && view() != null) {
                if (task.getSubtasks().size() > 0) {
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
            }
        });
    }

    @Override
    public void editTextChanged(EditTextModel model) {
        final Task task = this.task.getValue();
        if (model.equals(editTextName)) {
            task.setName(model.getText());
        } else if (model.equals(editTextDescription)) {
            task.setDescription(model.getText());
        }
    }

    @Override
    public void dateSelectionChanged(ButtonDateSelectionModel model) {
        final Task task = this.task.getValue();
        task.setDeadline(model.getDate());
    }

    @Override
    public void addSubtaskButtonClicked(ButtonAddSubtaskModel model) {
        final Task task = this.task.getValue();
        subtaskModels.add(new SubtaskModel(false, "", false));
        repository.update(task);
    }

    @Override
    public void subtaskChanged(SubtaskModel model) {
        final Task task = this.task.getValue();
        if (model.isDying()) {
            subtaskModels.remove(model);
            repository.update(task);
        }
    }

    @Override
    public void difficultChanged(SeekBarDifficultModel model) {
        final Task task = this.task.getValue();
        task.setDifficulty(model.getProgress());
    }

}