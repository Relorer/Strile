package com.example.strile.sevice.converters;

import android.util.Log;

import androidx.room.TypeConverter;

import com.example.strile.sevice.structures.DateCompleted;

import java.util.ArrayList;
import java.util.List;

public class DatesConverter {
    @TypeConverter
    public String fromDatesList(List<DateCompleted> dates) {
        StringBuilder data = new StringBuilder();

        if (dates != null) {
            for (DateCompleted d : dates) {
                data.append(d.date).append("-").append(d.complete).append(",");
            }
        }
        return data.toString();
    }

    @TypeConverter
    public List<DateCompleted> toDatesList(String data) {
        List<DateCompleted> datesList = new ArrayList<>();
        if (data != null) {
            String[] days = data.split(",");
            for (String day : days) {
                String[] fields = day.split("-");
                if (fields.length == 2) {
                    long date = Long.parseLong(fields[0]);
                    boolean complete = Boolean.parseBoolean(fields[1]);
                    datesList.add(new DateCompleted(date, complete));
                }
            }
        }
        return datesList;
    }
}
