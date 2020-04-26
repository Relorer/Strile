package com.example.strile.sevice;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.strile.sevice.event_handler_interfaces.OnChangeListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateManager {
    private static List<OnChangeListener<Long>> onChangeVisibleDayListeners = new ArrayList<>();

    private static long visibleDay = getDateWithoutTime(Calendar.getInstance().getTimeInMillis()).getTime();

    public static Date getDateWithoutTime(long day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String fromArrayDaysToString(boolean[] days) {
        int count = 0;
        for(boolean d : days) count += d ? 1 : 0;
        String text = "Repeat ";
        if (count == 7) text = "Repeat everyday";
        else if (count == 0) text = "No repeat";
        else {
            if (days[0]) text += "Sun, ";
            if (days[1]) text += "Mon, ";
            if (days[2]) text += "Tue, ";
            if (days[3]) text += "Wed, ";
            if (days[4]) text += "Thu, ";
            if (days[5]) text += "Fri, ";
            if (days[6]) text += "Sat, ";
            text = text.substring(0, text.length() - 2);
        }
        return text;
    }

    public static long getVisibleDay() {
        return visibleDay;
    }

    public static void setVisibleDay(long visibleDay) {
        DateManager.visibleDay = getDateWithoutTime(visibleDay).getTime();
        for (OnChangeListener<Long> listener : onChangeVisibleDayListeners)
            listener.onChanged(visibleDay);
    }

    public static void addOnChangeVisibleDayListener(@NonNull OnChangeListener<Long> onChangeListener) {
        if (!onChangeVisibleDayListeners.contains(onChangeListener))
            onChangeVisibleDayListeners.add(onChangeListener);
    }

    public static void removeOnChangeVisibleDayListener(@NonNull OnChangeListener<Long> onChangeListener) {
        onChangeVisibleDayListeners.remove(onChangeListener);
    }

    public static void clearOnChangeVisibleDayListeners() {
        onChangeVisibleDayListeners.clear();
    }
}
