package com.example.strile.infrastructure.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;

import com.example.strile.App;
import com.example.strile.data_firebase.models.Habit;
import com.example.strile.data_firebase.models.Task;
import com.example.strile.data_firebase.repositories.HabitRepository;
import com.example.strile.data_firebase.repositories.TaskRepository;
import com.example.strile.utilities.extensions.DateUtilities;
import com.example.strile.infrastructure.settings.UsersSettings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//todo move it in service class
public class NotificationPlanner {

    private static final App app;

    private static final AlarmManager manager;
    private static final TaskRepository taskRepository;
    private static final HabitRepository habitRepository;

    private static final LiveData<List<Task>> allTasks;
    private static final LiveData<List<Habit>> allHabits;

    private static final List<String> lastUpdatedHabitIds;
    private static final List<String> lastUpdatedTaskIds;

    static {
        app = App.getInstance();
        manager = (AlarmManager) app.getSystemService(Context.ALARM_SERVICE);
        taskRepository = new TaskRepository();
        habitRepository = new HabitRepository();

        lastUpdatedTaskIds = new ArrayList<>();
        lastUpdatedHabitIds = new ArrayList<>();

        allTasks = taskRepository.getAll();
        allTasks.observeForever(tasks -> {
            updateTasksNotify();
        });

        allHabits = habitRepository.getAll();
        allHabits.observeForever(habits -> {
            updateHabitsNotify();
        });
    }

    public static void updateTasksNotify() {
        List<Task> tasks = allTasks.getValue();
        if (tasks != null) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            int time = (int) UsersSettings.getTimeNotifyUpcomingTask();
            for (Task task : tasks) {
                calendar.setTimeInMillis(task.getDeadline());
                calendar.add(Calendar.MILLISECOND, time);
                int requestCode = (task.getId().hashCode());
                if (task.getDateComplete() == 0 && date.getTime() <= calendar.getTimeInMillis() && time != -1) {
                    restartNotify(task.getName(), task.getDescription(), calendar.getTimeInMillis(), requestCode);
                } else {
                    deleteNotify(requestCode);
                }
            }
            //check deleted tasks
            if (tasks.size() != lastUpdatedTaskIds.size()) {
                for (String id : lastUpdatedTaskIds) {
                    boolean deleted = true;
                    for (Task task : tasks) {
                        if (task.getId().equals(id)) {
                            deleted = false;
                            break;
                        }
                    }
                    if (deleted) deleteNotify(id.hashCode());
                }
                lastUpdatedTaskIds.clear();
                lastUpdatedTaskIds.addAll(tasks.stream().map(Task::getId).collect(Collectors.toList()));
            }
        }
    }

    public static void updateHabitsNotify() {
        List<Habit> habits = allHabits.getValue();
        if (habits != null) {
            Calendar calendar = Calendar.getInstance();
            for (Habit habit : habits) {
                int requestCode = habit.getId().hashCode();
                long notifyTime = habit.getNotificationTime();
                if (notifyTime != -1) {
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    if (calendar.getTimeInMillis() - DateUtilities.getDateOfDayWithoutTime(calendar.getTime()).getTime() > notifyTime) {
                        calendar.add(Calendar.DATE, 1);
                    }
                    while (!habit.plannedForDay(calendar.getTime()) || habit.isCompleteOnDay(calendar.getTime())) {
                        calendar.add(Calendar.DATE, 1);
                    }
                    restartNotify(habit.getName(), "",
                            DateUtilities.getDateOfDayWithoutTime(calendar.getTime()).getTime() + notifyTime,
                            requestCode);
                }
                else {
                    deleteNotify(requestCode);
                }
            }
            //check deleted habits
            if (habits.size() != lastUpdatedHabitIds.size()) {
                for (String id : lastUpdatedHabitIds) {
                    boolean deleted = true;
                    for (Habit habit : habits) {
                        if (habit.getId().equals(id)) {
                            deleted = false;
                            break;
                        }
                    }
                    if (deleted) deleteNotify(id.hashCode());
                }
                lastUpdatedHabitIds.clear();
                lastUpdatedHabitIds.addAll(habits.stream().map(Habit::getId).collect(Collectors.toList()));
            }
        }
    }

    private static void restartNotify(String title, String text, long time, int requestCode) {
        Intent intent = new Intent(app, NotificationReceiver.class);
        intent.putExtra(NotificationReceiver.NOTIFICATION_TITLE_ID, title);
        intent.putExtra(NotificationReceiver.NOTIFICATION_TEXT_ID, text);

        int flags = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_CANCEL_CURRENT;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(app, requestCode,
                intent, flags);

        manager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    public static void start() {
    }

    private static void deleteNotify(int requestCode) {
        Intent intent = new Intent(app, NotificationReceiver.class);

        int flags = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : PendingIntent.FLAG_CANCEL_CURRENT;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(app, requestCode,
                intent, flags);
        manager.cancel(pendingIntent);
    }
}