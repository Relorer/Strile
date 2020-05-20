package com.example.strile;

import android.app.Application;

import com.example.strile.service.progress.KeeperHistoryExecutions;
import com.example.strile.service.settings.UsersSettings;

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
        KeeperHistoryExecutions.start();
    }
}