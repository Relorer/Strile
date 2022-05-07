package com.example.strile.ui.screens.main.journal.cases.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.strile.data_firebase.models.Task;
import com.example.strile.data_firebase.repositories.TaskRepository;
import com.example.strile.ui.screens.main.journal.JournalFragment;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesPresenter;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.CaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_hiding.ButtonHidingModel;
import com.example.strile.infrastructure.rvadapter.items.task.TaskModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JournalTasksPresenter extends JournalCasesPresenter<JournalTasksFragment> {

    private final TaskRepository repository;
    private final LiveData<List<TaskModel>> taskModels;
    private final LiveData<List<Task>> tasks;

    private List<TaskModel> updatedList;
    private final TaskModelList modelList;

    public JournalTasksPresenter() {
        repository = new TaskRepository();
        tasks = repository.getAll();
        modelList = new TaskModelList();
        this.taskModels = Transformations.map(tasks, input -> input.stream()
                .map(model -> modelList.getModel(false, model, visibleDay))
                .collect(Collectors.toList()));
    }

    @Override
    public void setVisibleDay(Date visibleDay) {
        super.setVisibleDay(visibleDay);
        if (taskModels.getValue() != null && taskModels.getValue().size() > 0)
            buildDisplayedList(Objects.requireNonNull(this.tasks.getValue()).stream().map(model -> modelList.getModel(false, model, visibleDay)).collect(Collectors.toList()));
    }

    @Override
    public void itemClicked(CaseModel model) {
        Task task = ((TaskModel) model).getTask();
        if (task == null) return;

        JournalFragment fragment = (JournalFragment) view().getParentFragment();
        if (fragment != null)
            fragment.dismissSnackbar();

        view().startTaskActivity(task.getId());
    }

    @Override
    public void itemStateChanged(CaseModel model) {
        Task task = ((TaskModel) model).getTask();
        repository.update(task);
    }

    @Override
    public void buttonHidingStateChanged(ButtonHidingModel button) {
        super.buttonHidingStateChanged(button);
        buildDisplayedList(Objects.requireNonNull(this.tasks.getValue()).stream().map(model -> modelList.getModel(false, model, visibleDay)).collect(Collectors.toList()));
    }

    @Override
    protected void updateView() {
        if (updatedList != null) buildDisplayedList(updatedList);
        if (!taskModels.hasActiveObservers())
            taskModels.observe(view(), this::buildDisplayedList);
    }

    private void buildDisplayedList(List<TaskModel> tasks) {
        updatedList = tasks;

        if (view() != null) {
            final List<BaseModel> models = new ArrayList<>();
            final List<TaskModel> forDay = getPlannedForDay(tasks, visibleDay);
            final List<TaskModel> unfulfilled = getUnfulfilledOnDay(forDay, visibleDay);
            final int countCompleted = forDay.size() - unfulfilled.size();
            if (showCompleted) {
                models.addAll(forDay);
            } else {
                models.addAll(unfulfilled);
            }
            if (countCompleted > 0) {
                models.add(new ButtonHidingModel(idButton, false, showCompleted, countCompleted));
            }
            view().setSortedList(models);

            updatedList = null;
        }
    }

    private List<TaskModel> getPlannedForDay(List<TaskModel> models, Date day) {
        return models.stream()
                .filter(m -> m.getTask().plannedForDay(day))
                .collect(Collectors.toList());
    }

    private List<TaskModel> getUnfulfilledOnDay(List<TaskModel> models, Date day) {
        return models.stream()
                .filter(m -> !m.getTask().isCompleteOnDay(day))
                .collect(Collectors.toList());
    }
}