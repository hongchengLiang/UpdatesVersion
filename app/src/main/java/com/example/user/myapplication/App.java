package com.example.user.myapplication;

import android.app.Application;

import org.xutils.x;

/**
 * Created by user on 2017/7/24.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        BaseConfig baseConfig = new BaseConfig();
        BaseAndroid.init(baseConfig);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
