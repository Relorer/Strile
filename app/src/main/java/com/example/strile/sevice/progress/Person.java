package com.example.strile.sevice.progress;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.strile.R;
import com.example.strile.sevice.date.Day;

import java.util.Date;
import java.util.Locale;

public class Person {

    private final SharedPreferences preferences;

    private final String NAME = "name";
    private final String LEVEL = "level";
    private final String EXPERIENCE = "experience";
    private final String GOAL_EXPERIENCE = "goal_experience";
    private final String ACTIVE_DAYS = "active_days";
    private final String DATE_LAST_ACTIVE_DAY = "date_last_active_day";

    private final String noName;

    public Person(Context context, long personId) {
        preferences = context.getSharedPreferences(String.valueOf(personId), Context.MODE_PRIVATE);
        noName = context.getString(R.string.t_no_name);

        long date = preferences.getLong(DATE_LAST_ACTIVE_DAY, 0);
        long currentDate = new Day(new Date()).getDateOfDayWithoutTime().getTime();
        // > 24 hours
        if (currentDate - date > 24 * 60 * 60 * 1000) {
            preferences.edit().putInt(ACTIVE_DAYS, 0).apply();
        }
    }

    public String getName() {
        return preferences.getString(NAME, noName);
    }

    public void setName(String name) {
        preferences.edit().putString(NAME, name).apply();
    }

    public int getLevel() {
        return preferences.getInt(LEVEL, 1);
    }

    private void setLevel(int level) {
        preferences.edit().putInt(LEVEL, level).apply();
    }

    public int getExperience() {
        return preferences.getInt(EXPERIENCE, 0);
    }

    private void setExperience(int experience) {
        preferences.edit().putInt(EXPERIENCE, experience).apply();
    }

    public int getGoalExp() {
        return preferences.getInt(GOAL_EXPERIENCE, 100);
    }

    private void setGoalExp(int goalExp) {
        preferences.edit().putInt(GOAL_EXPERIENCE, goalExp).apply();
    }

    public int getActiveDays() {
        return preferences.getInt(ACTIVE_DAYS, 0);
    }

    private void addActiveDays(int count) {
        count += preferences.getInt(ACTIVE_DAYS, 0);
        preferences.edit().putInt(ACTIVE_DAYS, count).apply();
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
            addActiveDays(1);
            preferences.edit().putLong(DATE_LAST_ACTIVE_DAY, currentDate).apply();
        }
    }
}
