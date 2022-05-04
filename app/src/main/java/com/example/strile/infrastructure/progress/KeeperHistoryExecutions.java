package com.example.strile.infrastructure.progress;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.data_firebase.models.Executed;
import com.example.strile.data_firebase.models.Habit;
import com.example.strile.data_firebase.models.Task;
import com.example.strile.data_firebase.repositories.ExecutedRepository;
import com.example.strile.data_firebase.repositories.HabitRepository;
import com.example.strile.data_firebase.repositories.TaskRepository;
import com.example.strile.utilities.extensions.DateUtilities;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeeperHistoryExecutions {

    private static final ExecutedRepository executedRepository;
    private static final Map<String, Boolean> habitsState;
    private static final Map<String, Boolean> tasksState;

    private static final LiveData<List<Habit>> habitsLiveData;
    private static final LiveData<List<Task>> tasksLiveData;

    private static Date currentDay;

    static {
        App app = App.getInstance();
        HabitRepository habitRepository = new HabitRepository();
        TaskRepository taskRepository = new TaskRepository();
        executedRepository = new ExecutedRepository();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -8);
        executedRepository.deleteBeforeDate(calendar.getTime());

        habitsState = new HashMap<>();
        tasksState = new HashMap<>();

        habitsLiveData = habitRepository.getAll();
        tasksLiveData = taskRepository.getAll();

        setHabitsListener();
        setTasksListener();

        currentDay = DateUtilities.getDateOfDayWithoutTime(new Date());
    }

    private static void setHabitsListener() {
        habitsLiveData.observeForever(habits -> {
            if (currentDay.getTime() != DateUtilities.getDateOfDayWithoutTime(new Date()).getTime()) {
                tasksState.clear();
                habitsState.clear();
                currentDay = DateUtilities.getDateOfDayWithoutTime(new Date());
            }
            Date day = new Date();
            for (Habit habit : habits) {
                if (habitsState.containsKey(habit.getId())) {
                    Boolean prevValue = habitsState.get(habit.getId());
                    Boolean currValue = habit.isCompleteOnDay(day);
                    if (prevValue != currValue) {
                        int exp;
                        if (currValue) {
                            exp = (habit.streakByDay(day) + 1) * (habit.getDifficulty() + 1) / 2;
                            Executed executed = new Executed("", habit.getId(), habit.getName(), new Date().getTime(), exp, Habit.class.getCanonicalName());
                            executedRepository.update(executed);
                        } else {
                            exp = -1 * (habit.streakByDay(day) + 2) * (habit.getDifficulty() + 1) / 2;
                            executedRepository.deleteByCaseId(habit.getId(), Habit.class.getCanonicalName());
                        }
                        Progress.addExperience(exp);
                    }
                }
                habitsState.put(habit.getId(), habit.isCompleteOnDay(day));
            }
        });
    }

    private static void setTasksListener() {
        tasksLiveData.observeForever(tasks -> {
            if (currentDay.getTime() != DateUtilities.getDateOfDayWithoutTime(new Date()).getTime()) {
                tasksState.clear();
                habitsState.clear();
                currentDay = DateUtilities.getDateOfDayWithoutTime(new Date());
            }
            for (Task task : tasks) {
                if (tasksState.containsKey(task.getId())) {
                    Boolean prevValue = tasksState.get(task.getId());
                    Boolean currValue = task.getDateComplete() != 0;
                    if (prevValue != currValue) {
                        int exp;
                        if (currValue) {
                            exp = 10 * (task.getDifficulty() + 1);
                            Executed executed = new Executed("", task.getId(), task.getName(), new Date().getTime(), exp, Task.class.getCanonicalName());
                            executedRepository.update(executed);
                        }
                        else {
                            exp = -10 * (task.getDifficulty() + 1);
                            executedRepository.deleteByCaseId(task.getId(), Task.class.getCanonicalName());
                        }
                        Progress.addExperience(exp);
                    }
                }
                tasksState.put(task.getId(), task.getDateComplete() != 0);
            }
        });
    }

    public static void start() {
    }

}