package com.example.strile.sevice.recycler_view_adapter.models;

public class TaskModel extends BaseModel implements CaseModel {

    private final String name;
    private final long deadline;
    private final boolean complete;

    public TaskModel(boolean topMargin, String name, long deadline, boolean complete) {
        super(topMargin);
        this.name = name;
        this.deadline = deadline;
        this.complete = complete;
    }

    @Override
    public int getType() {
        return TASK_TYPE;
    }

    public String getName() {
        return name;
    }

    public long getDeadline() {
        return deadline;
    }

    public boolean isComplete() {
        return complete;
    }

    public TaskModel setState(boolean state) {
        return new TaskModel(isTopMargin(), name, deadline, state);
    }
}
