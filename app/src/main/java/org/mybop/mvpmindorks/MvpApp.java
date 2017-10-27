package org.mybop.mvpmindorks;

import android.app.Application;

import org.mybop.mvpmindorks.model.SharedPrefsHelper;
import org.mybop.mvpmindorks.model.UserManager;

public class MvpApp extends Application {

    private SharedPrefsHelper sharedPrefsHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        this.sharedPrefsHelper = new SharedPrefsHelper(this);
    }

    public UserManager getUserManager() {
        return new UserManager(sharedPrefsHelper);
    }
}
