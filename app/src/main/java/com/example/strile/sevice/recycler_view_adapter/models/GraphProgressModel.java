package com.example.strile.sevice.recycler_view_adapter.models;

public class GraphProgressModel extends BaseModel {

    private int maxHabit;
    private int maxTask;
    private int[] habitsByDays = {};
    private int[] tasksByDays = {};

    @Override
    public int getType() {
        return GRAPH_PROGRESS_TYPE;
    }

    public int getMaxHabit() {
        return maxHabit;
    }

    public void setMaxHabit(int maxHabit) {
        this.maxHabit = maxHabit;
        notifyOfChanges();
    }

    public int getMaxTask() {
        return maxTask;
    }

    public void setMaxTask(int maxTask) {
        this.maxTask = maxTask;
        notifyOfChanges();
    }

    public int[] getHabitsByDays() {
        return habitsByDays;
    }

    public void setHabitsByDays(int[] habitsByDays) {
        this.habitsByDays = habitsByDays;
        notifyOfChanges();
    }

    public int[] getTasksByDays() {
        return tasksByDays;
    }

    public void setTasksByDays(int[] tasksByDays) {
        this.tasksByDays = tasksByDays;
        notifyOfChanges();
    }
}
