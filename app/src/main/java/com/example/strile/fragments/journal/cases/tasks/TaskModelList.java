package com.example.strile.fragments.journal.cases.tasks;

import androidx.annotation.NonNull;

import com.example.strile.database.entities.Task;
import com.example.strile.sevice.recycler_view_adapter.items.task.TaskModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class TaskModelList {

    private final List<TaskModel> models = new ArrayList<>();

    public TaskModel getModel(boolean topMargin, @NonNull Task task, @NonNull Date date) {
        TaskModel model = models.stream()
                .filter(m -> m.getTask().getId() == task.getId())
                .findAny()
                .orElse(null);
        if (model == null) {
            model = new TaskModel(topMargin, task, date);
        } else {
            models.remove(model);
            model = new TaskModel(model.getId(), topMargin, task, date);
        }
        models.add(model);
        return model;
    }

}
