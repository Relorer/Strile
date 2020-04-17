package com.example.strile.sevice.recycler_view_adapter;

import android.content.SharedPreferences;

import androidx.room.PrimaryKey;

import com.example.strile.App;

public abstract class ItemModel {
    public static final String PREFERENCES_KEY_MAX_ID = "preferences_key_count_max_id";

    public static final int HABIT_TYPE = 1;
    public static final int TASK_TYPE = 2;
    public static final int BUTTON_HIDING = 3;

    private static int maxId = 0;

    private static SharedPreferences preferences;

    @PrimaryKey
    protected int id;

    public ItemModel() {
        maxId++;
        id = maxId;
        preferences.edit().putInt(PREFERENCES_KEY_MAX_ID, maxId).apply();
    }

    public abstract int getId();
    public abstract int getType();

    public static void setMaxId(int countItems) {
        ItemModel.maxId = countItems;
    }

    public static void setPreferences(SharedPreferences preferences) {
        ItemModel.preferences = preferences;
    }
}
