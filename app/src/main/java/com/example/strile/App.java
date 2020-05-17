package com.example.strile;

import android.app.Application;

import com.example.strile.sevice.progress.KeeperHistoryExecutions;

public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KeeperHistoryExecutions.start();
    }

    public static App getInstance() {
        return instance;
    }
}