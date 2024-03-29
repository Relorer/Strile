package com.example.strile.infrastructure.rvadapter.items.task;

import com.example.strile.data_firebase.models.Task;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.CaseModel;

import java.util.Date;

public class TaskModel extends BaseModel implements CaseModel {

    private final Task task;
    private Date dateComplete;

    public TaskModel(boolean topMargin, Task task, Date dateComplete) {
        super(topMargin);
        this.task = task;
        this.dateComplete = dateComplete;
    }

    public TaskModel(int id, boolean topMargin, Task task, Date dateComplete) {
        super(id, topMargin);
        this.task = task;
        this.dateComplete = dateComplete;
    }

    @Override
    public int getType() {
        return TASK_TYPE;
    }

    public String getName() {
        return task.getName();
    }

    public long getDeadline() {
        return task.getDeadline();
    }

    public boolean isComplete() {
        return task.isCompleteOnDay(dateComplete);
    }

    public TaskModel setState(boolean state) {
        task.setStateForDay(state, dateComplete);
        return this;
    }

    public Task getTask() {
        return task;
    }

    public TaskModel setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
        return this;
    }
}
