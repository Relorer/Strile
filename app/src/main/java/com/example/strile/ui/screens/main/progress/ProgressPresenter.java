package com.example.strile.ui.screens.main.progress;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.data_firebase.models.Executed;
import com.example.strile.data_firebase.models.Habit;
import com.example.strile.data_firebase.models.User;
import com.example.strile.data_firebase.repositories.ExecutedRepository;
import com.example.strile.data_firebase.repositories.UserRepository;
import com.example.strile.utilities.extensions.DateUtilities;
import com.example.strile.infrastructure.presenter.BasePresenter;
import com.example.strile.infrastructure.progress.Progress;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.graph_progress.GraphProgressModel;
import com.example.strile.infrastructure.rvadapter.items.info_progress.InfoProgressModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProgressPresenter extends BasePresenter<ProgressFragment> {

    private final LiveData<List<Executed>> executed;
    private final LiveData<User> user;

    private final ExecutedModelList executedModelList;

    private final int infoId;
    private final int graphId;

    public ProgressPresenter() {
        App app = App.getInstance();
        ExecutedRepository repository = new ExecutedRepository();
        executed = repository.getAll();
        user = new UserRepository().getCurrentUser();
        executedModelList = new ExecutedModelList();
        infoId = new InfoProgressModel(false, 0, 0, 0).getId();
        graphId = new GraphProgressModel(false, 0, 0, new int[0], new int[0]).getId();
    }

    @Override
    protected void updateView() {
        if (!executed.hasActiveObservers()) {
            executed.observe(view(), executed -> {
                _updateView();
            });
        }
        if (!user.hasActiveObservers()) {
            user.observe(view(), user -> {
                _updateView();
            });
        }
    }

    private void _updateView() {
        if (view() != null && executed.getValue() != null && user.getValue() != null) {
            final List<BaseModel> models = new ArrayList<>();
            models.add(new InfoProgressModel(infoId, false,
                    user.getValue().getLevel(),
                    user.getValue().getExperience(),
                    user.getValue().getGoalExperience() - user.getValue().getExperience()));
            models.add(createGraph(executed.getValue()));
            long today = DateUtilities.getDateOfDayWithoutTime(new Date()).getTime();
            models.addAll(executed.getValue().stream()
                    .filter(e -> e.getDateComplete() > today)
                    .map(e -> executedModelList.getModel(false, e.getName(), e.getExperience(), e.getDateComplete()))
                    .collect(Collectors.toList())
            );
            view().setSortedList(models);
        }
    }

    public void buttonSettingsClicked() {
        view().openSettings();
    }

    private GraphProgressModel createGraph(List<Executed> executed) {
        int[] habitsByDay = new int[14];
        int[] tasksByDay = new int[14];
        int maxHabits = 3;
        int maxTasks = 3;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtilities.getDateOfDayWithoutTime(new Date()));

        for (int i = 6; i >= 0; i--) {
            habitsByDay[2 * i] = calendar.get(Calendar.DATE);
            tasksByDay[2 * i] = calendar.get(Calendar.DATE);
            final long day = calendar.getTimeInMillis();
            List<Executed> daily = executed.stream().filter(e -> DateUtilities.getDateOfDayWithoutTime(new Date(e.getDateComplete())).getTime() == day).collect(Collectors.toList());
            for (Executed ex : daily) {
                if (ex.getTypeCase().equals(Habit.class.getCanonicalName())) {
                    habitsByDay[2 * i + 1] += 1;
                } else {
                    tasksByDay[2 * i + 1] += 1;
                }
            }
            maxHabits = Math.max(maxHabits, habitsByDay[2 * i + 1]);
            maxTasks = Math.max(maxTasks, tasksByDay[2 * i + 1]);
            calendar.add(Calendar.DATE, -1);
        }

        final GraphProgressModel graph = new GraphProgressModel(graphId, true,
                maxHabits, maxTasks,
                habitsByDay, tasksByDay);
        graph.setBottomMargin(true);
        return graph;
    }
}