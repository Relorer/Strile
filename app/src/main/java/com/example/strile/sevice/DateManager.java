package com.example.strile.sevice;

import java.util.Calendar;
import java.util.Date;

public class DateManager {
    public static Date getDateWithoutTimeUsingCalendar() {
        Calendar calendar = Calendar.getInstance();
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
}
