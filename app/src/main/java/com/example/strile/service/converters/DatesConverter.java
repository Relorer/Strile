package com.example.strile.service.converters;

import androidx.room.TypeConverter;

import com.example.strile.service.structures.DateCompleted;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatesConverter {

    private final Type type = new TypeToken<ArrayList<DateCompleted>>() {
    }.getType();

    @TypeConverter
    public String fromDatesList(List<DateCompleted> items) {
        return new Gson().toJson(items, type);
    }

    @TypeConverter
    public List<DateCompleted> toDatesList(String data) {
        return new Gson().fromJson(data, type);
    }
}