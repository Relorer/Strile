package com.example.strile.sevice.converters;

import android.util.Log;

import androidx.room.TypeConverter;

import com.example.strile.sevice.structures.DateCompleted;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DatesConverter {
    @TypeConverter
    public String fromDatesList(List<DateCompleted> items) {
        return new Gson().toJson(items, new TypeToken<ArrayList<DateCompleted>>() {}.getType());
    }

    @TypeConverter
    public List<DateCompleted> toDatesList(String data) {
        return new Gson().fromJson(data, new TypeToken<ArrayList<DateCompleted>>() {}.getType());
    }
}