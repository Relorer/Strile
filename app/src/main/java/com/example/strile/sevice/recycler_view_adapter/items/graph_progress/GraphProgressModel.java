package com.example.strile.sevice.recycler_view_adapter.items.graph_progress;

import androidx.annotation.NonNull;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class GraphProgressModel extends BaseModel {

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

    public GraphProgressModel(int id, boolean topMargin, int maxHabit, int maxTask, int[] habitsByDays, int[] tasksByDays) {
        super(id, topMargin);
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
