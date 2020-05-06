package com.example.strile.sevice.recycler_view_adapter.models;

public abstract class BaseModel {
    public static final int HABIT_TYPE = 1;
    public static final int TASK_TYPE = 2;
    public static final int SUBTASK_TYPE = 3;
    public static final int EDIT_TEXT_TYPE = 4;
    public static final int CURRENT_STREAK_TYPE = 5;
    public static final int DAY_TYPE = 6;
    public static final int GRAPH_PROGRESS_TYPE = 7;
    public static final int INFO_PROGRESS_TYPE = 8;
    public static final int COMPLETE_CASE_TYPE = 9;
    public static final int SEEK_BAR_DIFFICULT_TYPE = 10;
    public static final int PROGRESS_BAR_ELAPSED_TIME_TYPE = 11;
    public static final int BUTTON_HIDING_TYPE = 12;
    public static final int BUTTON_ADD_SUBTASK_TYPE = 13;
    public static final int BUTTON_TIME_GOAL_TYPE = 14;
    public static final int BUTTON_REPEAT_TYPE = 15;
    public static final int BUTTON_DATE_SELECTION_TYPE = 16;
    public static final int EXECUTED_TYPE = 17;

    private static int maxId = 0;

    private final int id;
    private final boolean topMargin;

    public BaseModel(boolean topMargin) {
        this.topMargin = topMargin;
        id = maxId;
        maxId++;
    }

    protected BaseModel(int id, boolean topMargin) {
        this.id = id;
        this.topMargin = topMargin;
    }

    public int getId() {
        return id;
    }

    public abstract int getType();

    public boolean isTopMargin() {
        return topMargin;
    }
}