package com.raghurana.singleactivityapp;

import android.app.Application;

public class MySingleActivityApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MessageBox.init(this);
    }

}
