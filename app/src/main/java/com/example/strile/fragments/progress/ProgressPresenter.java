package com.example.strile.fragments.progress;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.database.entities.Executed;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.repositories.ExecutedRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.sevice.date.Day;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.progress.Progress;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.GraphProgressModel;
import com.example.strile.sevice.recycler_view_adapter.models.InfoProgressModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProgressPresenter extends BasePresenter<ProgressFragment> {

    private final LiveData<List<Executed>> executed;

    private final ExecutedModelList executedModelList;

    private final int infoId;
    private final int graphId;

    public ProgressPresenter() {
        App app = App.getInstance();
        Repository<Executed> repository = new ExecutedRepository(app);
        executed = repository.getAll();
        executedModelList = new ExecutedModelList();
        infoId = new InfoProgressModel(false, 0, 0, 0).getId();
        graphId = new GraphProgressModel(false, 0, 0, new int[0], new int[0]).getId();
    }

    @Override
    protected void updateView() {
        if (!executed.hasActiveObservers()) {
            executed.observe(view(), executed -> {
                if (view() != null) {
                    final List<BaseModel> models = new ArrayList<>();
                    models.add(new InfoProgressModel(infoId, false,
                            Progress.getLevel(),
                            Progress.getExperience(),
                            Progress.getGoalExp() - Progress.getExperience()));
                    models.add(createGraph(executed));
                    models.addAll(executed.stream()
                            .limit(7)
                            .map(e -> executedModelList.getModel(false, e.getName(), e.getExperience(), e.getDateComplete()))
                            .collect(Collectors.toList())
                    );
                    view().setSortedList(models);
                }
            });
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
        calendar.setTime(Day.getDateOfDayWithoutTime(new Date()));

        for (int i = 6; i >= 0; i--) {
            habitsByDay[2 * i] = calendar.get(Calendar.DATE);
            tasksByDay[2 * i] = calendar.get(Calendar.DATE);
            final long day = calendar.getTimeInMillis();
            List<Executed> daily = executed.stream().filter(e -> Day.getDateOfDayWithoutTime(new Date(e.getDateComplete())).getTime() == day).collect(Collectors.toList());
            for (Executed ex : daily) {
                boolean complete = ex.getExperience() > 0;
                if (ex.getTypeCase().equals(Habit.class.getCanonicalName())) {
                    habitsByDay[2 * i + 1] += complete ? 1 : -1;
                } else {
                    tasksByDay[2 * i + 1] += complete ? 1 : -1;
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