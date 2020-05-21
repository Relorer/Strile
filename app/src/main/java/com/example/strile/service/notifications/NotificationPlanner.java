package com.example.strile.service.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.strile.App;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;
import com.example.strile.database.repositories.HabitRepository;
import com.example.strile.database.repositories.TaskRepository;
import com.example.strile.service.date.Day;

import java.util.Calendar;
import java.util.Date;

//todo move it in service class
public class NotificationPlanner {

    private static final App app;

    private static final AlarmManager manager;
    private static final TaskRepository taskRepository;
    private static final HabitRepository habitRepository;

    static {
        app = App.getInstance();
        manager = (AlarmManager) app.getSystemService(Context.ALARM_SERVICE);
        taskRepository = new TaskRepository(app);
        habitRepository = new HabitRepository(app);

        taskRepository.getAll().observeForever(tasks -> {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            for (Task task : tasks) {
                calendar.setTimeInMillis(task.getDeadline());
                calendar.add(Calendar.HOUR_OF_DAY, 9);
                int requestCode = (int) (task.getId() * 2 + 1);
                if (task.getDateComplete() == 0 && date.getTime() <= calendar.getTimeInMillis()) {
                    restartNotify(task.getName(), task.getDescription(), calendar.getTimeInMillis(), requestCode);
                } else {
                    deleteNotify(requestCode);
                }
            }
        });

        habitRepository.getAll().observeForever(habits -> {
            Calendar calendar = Calendar.getInstance();
            for (Habit habit : habits) {
                int requestCode = (int) (habit.getId() * 2);
                if (habit.getNotificationTime() != 0) {
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    if (calendar.getTimeInMillis() - Day.getDateOfDayWithoutTime(calendar.getTime()).getTime() >
                            habit.getNotificationTime()) {
                        calendar.add(Calendar.DATE, 1);
                    }
                    while (!habit.plannedForDay(calendar.getTime()) || habit.isCompleteOnDay(calendar.getTime())) {
                        calendar.add(Calendar.DATE, 1);
                    }
                    restartNotify(habit.getName(), "",
                            Day.getDateOfDayWithoutTime(calendar.getTime()).getTime() + habit.getNotificationTime(),
                            requestCode);
                }
                else {
                    deleteNotify(requestCode);
                }
            }
        });
    }

    private static void restartNotify(String title, String text, long time, int requestCode) {
        Intent intent = new Intent(app, NotificationReceiver.class);
        intent.putExtra(NotificationReceiver.NOTIFICATION_TITLE_ID, title);
        intent.putExtra(NotificationReceiver.NOTIFICATION_TEXT_ID, text);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(app, requestCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        manager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    public static void start() {
    }

    private static void deleteNotify(int requestCode) {
        Intent intent = new Intent(app, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(app, requestCode,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        manager.cancel(pendingIntent);
    }
}