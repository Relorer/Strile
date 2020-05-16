package com.example.strile.fragments.progress;

import com.example.strile.sevice.recycler_view_adapter.models.ExecutedModel;

import java.util.ArrayList;
import java.util.List;

public class ExecutedModelList {

    private final List<ExecutedModel> models = new ArrayList<>();

    public ExecutedModel getModel(boolean topMargin, String name, int experience, long dateCreate) {
        ExecutedModel model = models.stream()
                .filter(m -> m.getDateCreate() == dateCreate)
                .findAny()
                .orElse(null);
        if (model == null) {
            model = new ExecutedModel(topMargin, name, experience, dateCreate);
        }
        else {
            models.remove(model);
            model = new ExecutedModel(model.getId(), topMargin, name, experience, dateCreate);
        }
        models.add(model);
        return model;
    }

}
