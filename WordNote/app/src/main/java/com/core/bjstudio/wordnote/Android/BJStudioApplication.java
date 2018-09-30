package com.core.bjstudio.wordnote.Android;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import io.realm.Realm;

/**
 * Created by new_e on 2018-08-25.
 */

public class BJStudioApplication extends Application {

    public static boolean isDebug = false;

    @Override
    public void onCreate() {
        super.onCreate();
        this.init(this);
    }

    private void init(BJStudioApplication bjStudioApplication) {
        Realm.init(this);
        this.isDebug = this.isDebuggable(this);
        this.initBeans();
        this.initAutoWeired();
    }

    private void initBeans() {

    }

    private void initAutoWeired() {
    }

    /**
     * get Debug Mode
     *
     * @param context
     * @return
     */
    private boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }
        return debuggable;
    }

}
