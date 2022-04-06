package com.example.strile.infrastructure.progress;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.strile.App;
import com.example.strile.utilities.extensions.DateUtilities;

import java.util.Date;

public class Progress {

    private static final SharedPreferences preferences;

    private static final String PROGRESS_PREFERENCES = "progress_preferences";
    private static final String LEVEL = "level";
    private static final String EXPERIENCE = "experience";
    private static final String GOAL_EXPERIENCE = "goal_experience";
    private static final String ACTIVE_DAYS = "active_days";
    private static final String DATE_LAST_ACTIVE_DAY = "date_last_active_day";

    static {
        preferences = App.getInstance().getSharedPreferences(PROGRESS_PREFERENCES, Context.MODE_PRIVATE);
        long date = preferences.getLong(DATE_LAST_ACTIVE_DAY, 0);
        long currentDate = DateUtilities.getDateOfDayWithoutTime(new Date()).getTime();
        // > 24 hours
        if (currentDate - date > 24 * 60 * 60 * 1000) {
            preferences.edit().putInt(ACTIVE_DAYS, 0).apply();
        }
    }

    public static int getLevel() {
        return preferences.getInt(LEVEL, 1);
    }

    private static void setLevel(int level) {
        preferences.edit().putInt(LEVEL, level).apply();
    }

    public static int getExperience() {
        return preferences.getInt(EXPERIENCE, 0);
    }

    private static void setExperience(int experience) {
        preferences.edit().putInt(EXPERIENCE, experience).apply();
    }

    public static int getGoalExp() {
        return preferences.getInt(GOAL_EXPERIENCE, 100);
    }

    private static void setGoalExp(int goalExp) {
        preferences.edit().putInt(GOAL_EXPERIENCE, goalExp).apply();
    }

    public static int getActiveDays() {
        return preferences.getInt(ACTIVE_DAYS, 0);
    }

    public static void addExperience(int experience) {
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
        long currentDate = DateUtilities.getDateOfDayWithoutTime(new Date()).getTime();
        if (date < currentDate) {
            addActiveDay();
            preferences.edit().putLong(DATE_LAST_ACTIVE_DAY, currentDate).apply();
        }
    }

    private static void addActiveDay() {
        int count = 1 + preferences.getInt(ACTIVE_DAYS, 0);
        preferences.edit().putInt(ACTIVE_DAYS, count).apply();
    }
}
