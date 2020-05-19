package com.example.strile.sevice.converters;

import androidx.room.TypeConverter;

import com.example.strile.sevice.structures.Subtask;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SubtasksConverter {

    private final Type type = new TypeToken<ArrayList<Subtask>>() {
    }.getType();

    @TypeConverter
    public String fromList(List<Subtask> items) {
        return new Gson().toJson(items, type);
    }

    @TypeConverter
    public List<Subtask> toList(String data) {
        return new Gson().fromJson(data, type);
    }
}