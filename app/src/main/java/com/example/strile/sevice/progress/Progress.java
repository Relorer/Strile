package com.example.strile.sevice.progress;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.strile.sevice.date.Day;

import java.util.Date;

public class Progress {

    private static Progress instance;

    private final SharedPreferences preferences;

    private final String PROGRESS_PREFERENCES = "progress_preferences";
    private final String LEVEL = "level";
    private final String EXPERIENCE = "experience";
    private final String GOAL_EXPERIENCE = "goal_experience";
    private final String ACTIVE_DAYS = "active_days";
    private final String DATE_LAST_ACTIVE_DAY = "date_last_active_day";

    public static Progress getInstance(Context context) {
        if (instance == null) instance = new Progress(context);
        return instance;
    }

    private Progress(Context context) {
        preferences = context.getSharedPreferences(PROGRESS_PREFERENCES, Context.MODE_PRIVATE);
        long date = preferences.getLong(DATE_LAST_ACTIVE_DAY, 0);
        long currentDate = new Day(new Date()).getDateOfDayWithoutTime().getTime();
        // > 24 hours
        if (currentDate - date > 24 * 60 * 60 * 1000) {
            preferences.edit().putInt(ACTIVE_DAYS, 0).apply();
        }
    }

    public int getLevel() {
        return preferences.getInt(LEVEL, 1);
    }

    public int getExperience() {
        return preferences.getInt(EXPERIENCE, 0);
    }

    public int getGoalExp() {
        return preferences.getInt(GOAL_EXPERIENCE, 100);
    }

    public int getActiveDays() {
        return preferences.getInt(ACTIVE_DAYS, 0);
    }

    public void addExperience(int experience) {
        int totalExp = experience + getExperience();
        int goalExp = getGoalExp();
        if (totalExp < goalExp) {
            setExperience(Math.max(totalExp, 0));
        } else {
            int level = getLevel();
            while (totalExp > goalExp) {
                level++;
                totalExp -= goalExp;
                goalExp = goalExp * 3 / 2;
            }
            setExperience(totalExp);
            setGoalExp(goalExp);
            setLevel(level);
        }
        long date = preferences.getLong(DATE_LAST_ACTIVE_DAY, 0);
        long currentDate = new Day(new Date()).getDateOfDayWithoutTime().getTime();
        if (date < currentDate) {
            addActiveDay();
            preferences.edit().putLong(DATE_LAST_ACTIVE_DAY, currentDate).apply();
        }
    }

    private void addActiveDay() {
        int count = 1 + preferences.getInt(ACTIVE_DAYS, 0);
        preferences.edit().putInt(ACTIVE_DAYS, count).apply();
    }

    private void setExperience(int experience) {
        preferences.edit().putInt(EXPERIENCE, experience).apply();
    }

    private void setGoalExp(int goalExp) {
        preferences.edit().putInt(GOAL_EXPERIENCE, goalExp).apply();
    }

    private void setLevel(int level) {
        preferences.edit().putInt(LEVEL, level).apply();
    }
}
