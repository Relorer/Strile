package com.example.strile.infrastructure.rvadapter.items;

public abstract class BaseModel {
    public static final int HABIT_TYPE = 1;
    public static final int TASK_TYPE = 2;
    public static final int SUBTASK_TYPE = 3;
    public static final int EDIT_TEXT_TYPE = 4;
    public static final int EDIT_NUMBER_TYPE = 5;
    public static final int CURRENT_STREAK_TYPE = 6;
    public static final int DAY_TYPE = 7;
    public static final int GRAPH_PROGRESS_TYPE = 8;
    public static final int INFO_PROGRESS_TYPE = 9;
    public static final int EXECUTED_TYPE = 10;
    public static final int SEEK_BAR_DIFFICULT_TYPE = 11;
    public static final int PROGRESS_BAR_ELAPSED_TIME_TYPE = 12;
    public static final int BUTTON_HIDING_TYPE = 13;
    public static final int BUTTON_ADD_SUBTASK_TYPE = 14;
    public static final int BUTTON_TIME_GOAL_TYPE = 15;
    public static final int BUTTON_REPEAT_TYPE = 16;
    public static final int BUTTON_DATE_SELECTION_TYPE = 17;
    public static final int BUTTON_NIGHT_MODE_SELECTION_TYPE = 18;
    public static final int BUTTON_POMODORO_TIMER_SETTINGS_TYPE = 19;
    public static final int BUTTON_LANGUAGE_SELECTION_TYPE = 20;
    public static final int BUTTON_TASK_TIME_NOTIFY_SELECTION_TYPE = 21;
    public static final int BUTTON_TIME_SELECTION_TYPE = 22;
    public static final int SWITCH_SETTING_TYPE = 23;

    private static int maxId = 0;

    private final int id;
    private final boolean topMargin;
    private boolean bottomMargin;

    protected BaseModel(boolean topMargin) {
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

    public boolean isBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(boolean bottomMargin) {
        this.bottomMargin = bottomMargin;
    }
}