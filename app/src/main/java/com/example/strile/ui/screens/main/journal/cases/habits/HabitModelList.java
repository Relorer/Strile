package com.example.strile.ui.screens.main.journal.cases.habits;

import androidx.annotation.NonNull;

import com.example.strile.data_firebase.models.Habit;
import com.example.strile.infrastructure.rvadapter.items.habit.HabitModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class HabitModelList {

    private final List<HabitModel> models = new ArrayList<>();

    public HabitModel getModel(boolean topMargin, @NonNull Habit habit, @NonNull Date date) {
        HabitModel model = models.stream()
                .filter(m -> m.getHabit().getId() == habit.getId())
                .findAny()
                .orElse(null);
        if (model == null) {
            model = new HabitModel(topMargin, habit, date);
        } else {
            models.remove(model);
            model = new HabitModel(model.getId(), topMargin, habit, date);
        }
        models.add(model);
        return model;
    }

}