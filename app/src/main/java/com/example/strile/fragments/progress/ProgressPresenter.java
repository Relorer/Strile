package com.example.strile.fragments.progress;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.database.entities.Executed;
import com.example.strile.database.repositories.ExecutedRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.progress.Person;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.GraphProgressModel;
import com.example.strile.sevice.recycler_view_adapter.models.InfoProgressModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgressPresenter extends BasePresenter<ProgressFragment> {

    private final Repository<Executed> repository;
    private final LiveData<List<Executed>> executed;

    private final ExecutedModelList executedModelList;

    private final int infoId;
    private final int graphId;

    private Person person;

    public ProgressPresenter() {
        repository = new ExecutedRepository(App.getInstance());
        executed = repository.getAll();
        executedModelList = new ExecutedModelList();
        infoId = new InfoProgressModel(false, "", 0, 0, 0, 0).getId();
        graphId = new GraphProgressModel(false, 0, 0, new int[0], new int[0]).getId();
    }

    @Override
    protected void updateView() {
        person = new Person(view().getContext(), 0);
        executed.observe(view(), executed -> {
            if (view() != null) {
                final List<BaseModel> models = new ArrayList<>();
                models.add(new InfoProgressModel(infoId, false,
                        person.getName(),
                        person.getActiveDays(),
                        person.getLevel(),
                        person.getExperience(),
                        person.getGoalExp() - person.getExperience()));
                final GraphProgressModel graph = new GraphProgressModel(graphId, true,
                        100, 10,
                        new int[14], new int[14]);
                graph.setBottomMargin(true);
                models.add(graph);
                models.addAll(executed.stream()
                        .limit(7)
                        .map(e -> executedModelList.getModel(false, e.getName(), e.getExperience(), e.getDateComplete()))
                        .collect(Collectors.toList())
                );
                view().setSortedList(models);
            }
        });
    }

    public void buttonSettingsClicked() {
        view().openSettings();
    }
}