package com.example.library.activity;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


import org.androidannotations.annotations.EApplication;

public class Application extends MultiDexApplication {

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);    //To change body of overridden methods use File | Settings | File Templates.
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getContext() {
        return mContext;
    }

}
