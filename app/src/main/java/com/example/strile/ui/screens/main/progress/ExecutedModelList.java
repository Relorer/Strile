package com.example.strile.ui.screens.main.progress;

import com.example.strile.infrastructure.rvadapter.items.executed.ExecutedModel;

import java.util.ArrayList;
import java.util.List;

class ExecutedModelList {

    private final List<ExecutedModel> models = new ArrayList<>();

    public ExecutedModel getModel(boolean topMargin, String name, int experience, long dateCreate) {
        ExecutedModel model = models.stream()
                .filter(m -> m.getDateCreate() == dateCreate)
                .findAny()
                .orElse(null);
        if (model == null) {
            model = new ExecutedModel(topMargin, name, experience, dateCreate);
        } else {
            models.remove(model);
            model = new ExecutedModel(model.getId(), topMargin, name, experience, dateCreate);
        }
        models.add(model);
        return model;
    }

}
