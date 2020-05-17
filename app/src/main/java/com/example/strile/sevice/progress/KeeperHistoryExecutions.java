package com.example.strile.sevice.progress;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.database.entities.Executed;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;
import com.example.strile.database.repositories.ExecutedRepository;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.Repository;
import com.example.strile.database.repositories.TaskRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeeperHistoryExecutions {

    private static KeeperHistoryExecutions instance;

    private LifecycleOwner owner;

    private final ExecutedRepository executedRepository;
    private final Map<Long, Boolean> habitsState;
    private final Map<Long, Boolean> tasksState;

    private final LiveData<List<Habit>> habitsLiveData;
    private final LiveData<List<Task>> tasksLiveData;

    private final Progress progress;

    private KeeperHistoryExecutions(@NonNull LifecycleOwner owner, Progress progress) {
        App app = App.getInstance();
        Repository<Habit> habitRepository = new HabitRepository(app);
        Repository<Task> taskRepository = new TaskRepository(app);
        executedRepository = new ExecutedRepository(app);

        habitsState = new HashMap<>();
        tasksState = new HashMap<>();

        this.owner = owner;
        this.progress = progress;

        habitsLiveData = habitRepository.getAll();
        tasksLiveData = taskRepository.getAll();

        setHabitsListener();
        setTasksListener();
    }

    private void setHabitsListener() {
        habitsLiveData.observe(owner, habits -> {
            Date day = new Date();
            for (Habit habit : habits) {
                if (habitsState.containsKey(habit.getId())) {
                    Boolean prevValue = habitsState.get(habit.getId());
                    Boolean currValue = habit.isCompleteOnDay(day);
                    if (prevValue != currValue) {
                        int exp;
                        if (currValue) {
                            exp = (habit.getStreakByDay(day) + 1) * (habit.getDifficulty() + 1) / 2;
                        } else {
                            exp = -1 * (habit.getStreakByDay(day) + 2) * (habit.getDifficulty() + 1) / 2;
                        }
                        progress.addExperience(exp);
                        Executed executed = new Executed(habit.getId(), habit.getName(), new Date().getTime(), exp, Habit.class.getCanonicalName());
                        executedRepository.insert(executed);
                    }
                }
                habitsState.put(habit.getId(), habit.isCompleteOnDay(day));
            }
            if (habits.size() < habitsState.size()) {
                removeOldDataHabits(habits);
            }
        });
    }

    private void removeOldDataHabits(List<Habit> habits) {
        for (Map.Entry entry : habitsState.entrySet()) {
            final long habitId = (Long) entry.getKey();
            Habit habit = habits.stream().filter(m -> m.getId() == habitId).findAny().orElse(null);
            if (habit == null) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    Habit _habit = null;
                    if (habitsLiveData.getValue() != null)
                        _habit = habitsLiveData.getValue().stream().filter(m -> m.getId() == habitId).findAny().orElse(null);
                    if (_habit == null) {
                        executedRepository.deleteByCaseId(habitId, Habit.class.getCanonicalName());
                    }
                }, 7000);
            }
        }
    }

    private void setTasksListener() {
        tasksLiveData.observe(owner, tasks -> {
            for (Task task : tasks) {
                if (tasksState.containsKey(task.getId())) {
                    Boolean prevValue = tasksState.get(task.getId());
                    Boolean currValue = task.getDateComplete() != 0;
                    if (prevValue != currValue) {
                        int exp = 10 * (task.getDifficulty() + 1);
                        exp *= currValue ? 1 : -1;
                        progress.addExperience(exp);
                        Executed executed = new Executed(task.getId(), task.getName(), new Date().getTime(), exp, Task.class.getCanonicalName());
                        executedRepository.insert(executed);
                    }
                }
                tasksState.put(task.getId(), task.getDateComplete() != 0);
            }
            if (tasks.size() < tasksState.size()) {
                removeOldDataTasks(tasks);
            }
        });
    }

    private void removeOldDataTasks(List<Task> tasks) {
        for (Map.Entry entry : tasksState.entrySet()) {
            final long taskId = (Long) entry.getKey();
            Task task = tasks.stream().filter(m -> m.getId() == taskId).findAny().orElse(null);
            if (task == null) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    Task _task = null;
                    if (tasksLiveData.getValue() != null)
                        _task = tasksLiveData.getValue().stream().filter(m -> m.getId() == taskId).findAny().orElse(null);
                    if (_task == null) {
                        executedRepository.deleteByCaseId(taskId, Task.class.getCanonicalName());
                    }
                }, 7000);
            }
        }
    }

    public static KeeperHistoryExecutions getInstance(@NonNull LifecycleOwner owner, Progress progress) {
        if (instance != null && instance.owner == owner) {
            return instance;
        } else if (instance != null) {
            instance.habitsLiveData.removeObservers(instance.owner);
            instance.tasksLiveData.removeObservers(instance.owner);
        }
        instance = new KeeperHistoryExecutions(owner, progress);
        return instance;
    }
}