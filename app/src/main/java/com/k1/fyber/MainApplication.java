package com.k1.fyber;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by K1 on 8/23/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
