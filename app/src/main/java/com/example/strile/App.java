package com.example.strile;

import android.app.Application;

import com.example.strile.infrastructure.notifications.NotificationPlanner;
import com.example.strile.infrastructure.settings.UsersSettings;

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        UsersSettings.start();
        NotificationPlanner.start();
    }
}