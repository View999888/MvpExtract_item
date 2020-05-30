package com.wkx.contextUtils;

import android.app.Application;
import android.content.Context;

public class MyAppcation extends Application {
    private static MyAppcation mApplication;
    private static Context mApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
    }

    public static MyAppcation getmApplication() {
        return mApplication;
    }

    public static Context getmApplicationContext() {
        return mApplicationContext;
    }
}
