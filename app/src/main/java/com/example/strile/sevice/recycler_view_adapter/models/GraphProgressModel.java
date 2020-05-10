package com.example.strile.sevice.recycler_view_adapter.models;

import androidx.annotation.NonNull;

public class GraphProgressModel extends BaseModel {

    //todo graph add setters for params

    private final int maxHabit;
    private final int maxTask;
    private final int[] habitsByDays;
    private final int[] tasksByDays;

    public GraphProgressModel(boolean topMargin, int maxHabit, int maxTask,
                              @NonNull int[] habitsByDays,
                              @NonNull int[] tasksByDays) {
        super(topMargin);
        this.maxHabit = maxHabit;
        this.maxTask = maxTask;
        this.habitsByDays = habitsByDays;
        this.tasksByDays = tasksByDays;
    }

    @Override
    public int getType() {
        return GRAPH_PROGRESS_TYPE;
    }

    public int getMaxHabit() {
        return maxHabit;
    }

    public int getMaxTask() {
        return maxTask;
    }

    public int[] getHabitsByDays() {
        return habitsByDays;
    }

    public int[] getTasksByDays() {
        return tasksByDays;
    }
}
