package com.example.strile.sevice.converters;

import androidx.room.TypeConverter;

import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SubtasksConverter {
    @TypeConverter
    public String fromList(List<SubtaskModel> items) {
        return new Gson().toJson(items, new TypeToken<ArrayList<SubtaskModel>>() {}.getType());
    }

    @TypeConverter
    public List<SubtaskModel> toList(String data) {
        return new Gson().fromJson(data, new TypeToken<ArrayList<SubtaskModel>>() {}.getType());
    }
}
