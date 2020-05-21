package com.example.strile.service.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.strile.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationDisplay {

    private static final int MAX_AVAILABLE_ID = 200;
    private static int id = 201;
    private static final List<String> ids = new ArrayList<>();

    private final String CHANNEL_ID = "MAIN_CHANNEL_ID";
    private final NotificationManager notificationManager;

    private final Context context;

    public NotificationDisplay(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.context = context;
        createChannelIfNeeded();
    }

    public void showNotification(String title, String text, Intent intent, String id) {
        int index = ids.indexOf(id);
        if (index < 0) {
            index = ids.size();
            ids.add(id);
            if (index > MAX_AVAILABLE_ID) throw new IndexOutOfBoundsException("array of ids is full");
        }
        _showNotification(title, text, intent, index);
    }

    public void showNotification(String title, String text, Intent intent) {
        _showNotification(title, text, intent, id);
        id++;
    }

    private void _showNotification(String title, String text, Intent intent, int id) {
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.tick).setColor(context.getColor(R.color.colorAccent))
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setPriority(NotificationCompat.PRIORITY_LOW);
        if (!text.equals("")) notificationBuilder.setContentText(text);
        notificationManager.notify(id, notificationBuilder.build());
    }

    private void createChannelIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }
}