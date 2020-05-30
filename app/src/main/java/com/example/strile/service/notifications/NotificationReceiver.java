package com.example.strile.service.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.strile.activities.splash.SplashActivity;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_TITLE_ID = "notification_title_id";
    public static final String NOTIFICATION_TEXT_ID = "notification_text_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationDisplay notificationDisplay = new NotificationDisplay(context);

        Intent startIntent = new Intent(context, SplashActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        if (intent.getExtras() != null) {
            String title = intent.getExtras().getString(NOTIFICATION_TITLE_ID, "Title");
            String text = intent.getExtras().getString(NOTIFICATION_TEXT_ID, "text");
                notificationDisplay.showNotification(title, text, startIntent);
        }

        NotificationPlanner.updateHabitsNotify();
    }

}