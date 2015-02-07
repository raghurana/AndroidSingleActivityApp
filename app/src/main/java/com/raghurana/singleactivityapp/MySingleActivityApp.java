package com.raghurana.singleactivityapp;

import android.app.Application;

import com.raghurana.singleactivityapp.utilities.DisplayUtil;

public class MySingleActivityApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MessageBox.init(this);
        DisplayUtil.logPhoneScreenDensity(this);
    }

}
