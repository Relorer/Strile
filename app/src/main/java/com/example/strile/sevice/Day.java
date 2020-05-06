package com.example.strile.sevice;

import java.util.Calendar;
import java.util.Date;

public class Day {

    private final Date date;

    public Day(Date date) {
        this.date = date;
    }

    public Date getDateOfDayWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}