package com.codepath.simpletodo;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by ilyaseletsky on 7/31/17.
 */
public class SimpleTodoApplication extends Application {
    /**
     * Doing this so my enum can return a localized toString.
     * https://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context/4391811#4391811
     */
    private static Context staticContextRef;

    @Override
    public void onCreate() {
        super.onCreate();

        staticContextRef = this;

        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        // add for verbose logging
        // FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }

    public static Context getContext(){
        return staticContextRef;
    }
}
