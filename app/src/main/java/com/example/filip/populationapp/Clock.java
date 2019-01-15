package com.example.filip.populationapp;

import android.app.Application;
import android.os.SystemClock;

public class Clock extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
