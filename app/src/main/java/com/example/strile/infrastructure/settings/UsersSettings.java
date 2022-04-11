package com.example.strile.infrastructure.settings;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.strile.App;
import com.example.strile.infrastructure.notifications.NotificationPlanner;

public class UsersSettings {

    private static final String USERS_SETTINGS_PREFERENCES = "users_settings_preferences";
    private static final String NIGHT_MODE_PREFERENCE = "night_mode_preference";
    private static final String TIMER_POMODORO_TIME_GOAL = "timer_pmodoro_time_goal";
    private static final String TIMER_SHORT_BREAK_TIME_GOAL = "timer_short_break_time_goal";
    private static final String TIMER_LONG_BREAK_TIME_GOAL = "timer_long_break_time_goal";
    private static final String TIMER_FREQUENCY_LONG_BREAK = "timer_frequency_long_break";
    private static final String NOTIFY_AFTER_TIMER_ENDS = "notify_after_timer_ends";
    private static final String TIME_NOTIFY_UPCOMING_TASK = "time_notify_upcoming_task";
    private static final String USER_SKIP_AUTH = "user_skip_auth";

    private static final SharedPreferences preferences;

    static {
        preferences = App.getInstance().getSharedPreferences(USERS_SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
        int nightMode = preferences.getInt(NIGHT_MODE_PREFERENCE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }

    @AppCompatDelegate.NightMode
    public static int getNightMode() {
        return preferences.getInt(NIGHT_MODE_PREFERENCE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    public static void setNightMode(@AppCompatDelegate.NightMode int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
        preferences.edit().putInt(NIGHT_MODE_PREFERENCE, mode).apply();
    }

    public static long getTimerPomodoroTimeGoal() {
        return preferences.getLong(TIMER_POMODORO_TIME_GOAL, 25_000 * 60);
    }

    public static void setTimerPomodoroTimeGoal(long time) {
        preferences.edit().putLong(TIMER_POMODORO_TIME_GOAL, time).apply();
    }

    public static long getTimerShortBreakTimeGoal() {
        return preferences.getLong(TIMER_SHORT_BREAK_TIME_GOAL, 5_000 * 60);
    }

    public static void setTimerShortBreakTimeGoal(long time) {
        preferences.edit().putLong(TIMER_SHORT_BREAK_TIME_GOAL, time).apply();
    }

    public static long getTimerLongBreakTimeGoal() {
        return preferences.getLong(TIMER_LONG_BREAK_TIME_GOAL, 15_000 * 60);
    }

    public static void setTimerLongBreakTimeGoal(long time) {
        preferences.edit().putLong(TIMER_LONG_BREAK_TIME_GOAL, time).apply();
    }

    public static int getTimerFrequencyLongBreak() {
        return preferences.getInt(TIMER_FREQUENCY_LONG_BREAK, 4);
    }

    public static void setTimerFrequencyLongBreak(int time) {
        preferences.edit().putInt(TIMER_FREQUENCY_LONG_BREAK, time).apply();
    }

    public static boolean isNotifyAfterTimerEnd() {
        return preferences.getBoolean(NOTIFY_AFTER_TIMER_ENDS, true);
    }

    public static void setNotifyAfterTimerEnds(boolean state) {
        preferences.edit().putBoolean(NOTIFY_AFTER_TIMER_ENDS, state).apply();
    }

    public static long getTimeNotifyUpcomingTask() {
        return preferences.getLong(TIME_NOTIFY_UPCOMING_TASK, 9 * 60 * 60_000);
    }

    public static void setTimeNotifyUpcomingTask(long time) {
        preferences.edit().putLong(TIME_NOTIFY_UPCOMING_TASK, time).apply();
        NotificationPlanner.updateTasksNotify();
    }

    public static Boolean getUserSkipAuth() {
        return preferences.getBoolean(USER_SKIP_AUTH, false);
    }

    public static void setUserSkipAuth(Boolean value) {
        preferences.edit().putBoolean(USER_SKIP_AUTH, value).apply();
    }

    public static void start() {
    }
}
