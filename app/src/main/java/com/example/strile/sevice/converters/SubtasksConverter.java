package com.example.strile.sevice.converters;

import androidx.room.TypeConverter;

import com.example.strile.sevice.structures.DateCompleted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubtasksConverter {
    private final String separator = "<subtask_separator>";

    @TypeConverter
    public String fromSubtasksList(List<String> names) {
        StringBuilder data = new StringBuilder();

        if (names != null) {
            for (String n : names) {
                data.append(n).append(separator);
            }
        }

        return data.toString();
    }

    @TypeConverter
    public List<String> toDatesList(String data) {
        return new ArrayList<>(Arrays.asList(data.split(separator)));
    }
}
