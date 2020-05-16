package com.example.strile.sevice.progress;

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
import com.example.strile.sevice.date.Day;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeeperHistoryExecutions {

    private static KeeperHistoryExecutions instance;

    private LifecycleOwner owner;

    private final Repository<Executed> executedRepository;
    private final Map<Long, Boolean> habitsState;
    private final Map<Long, Boolean> tasksState;

    private final LiveData<List<Habit>> habitsLiveData;
    private final LiveData<List<Task>> tasksLiveData;

    private final Person person;

    private KeeperHistoryExecutions(@NonNull LifecycleOwner owner, Person person) {
        App app = App.getInstance();
        Repository<Habit> habitRepository = new HabitRepository(app);
        Repository<Task> taskRepository = new TaskRepository(app);
        executedRepository = new ExecutedRepository(app);

        habitsState = new HashMap<>();
        tasksState = new HashMap<>();

        this.owner = owner;
        this.person = person;

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
                        person.addExperience(exp);
                        Executed executed = new Executed(habit.getId(), habit.getName(), new Date().getTime(), exp, Habit.class.getCanonicalName());
                        executedRepository.insert(executed);
                    }
                }
                habitsState.put(habit.getId(), habit.isCompleteOnDay(day));
            }
        });
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
                        person.addExperience(exp);
                        Executed executed = new Executed(task.getId(), task.getName(), new Date().getTime(), exp, Task.class.getCanonicalName());
                        executedRepository.insert(executed);
                    }
                }
                tasksState.put(task.getId(), task.getDateComplete() != 0);
            }
        });
    }

    public static KeeperHistoryExecutions getInstance(@NonNull LifecycleOwner owner, Person person) {
        if (instance != null && instance.owner == owner) {
            return instance;
        }
        else if (instance != null) {
            instance.habitsLiveData.removeObservers(instance.owner);
            instance.tasksLiveData.removeObservers(instance.owner);
        }
        instance = new KeeperHistoryExecutions(owner, person);
        return instance;
    }
}