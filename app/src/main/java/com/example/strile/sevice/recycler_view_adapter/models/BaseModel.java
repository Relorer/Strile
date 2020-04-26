package com.example.strile.sevice.recycler_view_adapter.models;

import android.content.SharedPreferences;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.strile.sevice.event_handler_interfaces.OnChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;

public abstract class BaseModel {
    public static final String PREFERENCES_KEY_MAX_ID = "preferences_key_count_max_id";

    public static final int HABIT_TYPE = 1;
    public static final int TASK_TYPE = 2;
    public static final int BUTTON_HIDING_TYPE = 3;
    public static final int BUTTON_ADD_SUBTASK_TYPE = 4;
    public static final int BUTTON_TIME_GOAL_TYPE = 5;
    public static final int BUTTON_REPEAT_TYPE = 6;
    public static final int SUBTASK_TYPE = 7;
    public static final int SEEK_BAR_DIFFICULT_TYPE = 8;
    public static final int BUTTON_DATE_SELECTION_TYPE = 9;
    public static final int EDIT_TEXT_TYPE = 10;
    public static final int PROGRESS_BAR_ELAPSED_TIME_TYPE = 11;
    public static final int CURRENT_STREAK_TYPE = 12;
    public static final int DAY_TYPE = 13;

    private static int maxId = 0;

    private static SharedPreferences preferences;

    @Ignore
    private OnModelChangedListener onChangeListener;

    @PrimaryKey
    protected int id;

    private boolean topMargin = false;

    public BaseModel() {
        maxId++;
        id = maxId;
        preferences.edit().putInt(PREFERENCES_KEY_MAX_ID, maxId).apply();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract int getType();

    public static void setMaxId(int countItems) {
        BaseModel.maxId = countItems;
    }

    public static void setPreferences(SharedPreferences preferences) {
        BaseModel.preferences = preferences;
    }

    public boolean isTopMargin() {
        return topMargin;
    }

    public void setTopMargin(boolean topMargin) {
        this.topMargin = topMargin;
    }

    public void setOnChangeListener(OnModelChangedListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    protected void notifyOfChanges() {
        if (onChangeListener != null)
            onChangeListener.onChanged(this);
    }
}
