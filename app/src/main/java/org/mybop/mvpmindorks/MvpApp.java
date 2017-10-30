package org.mybop.mvpmindorks;

import android.app.Application;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.smoothie.module.SmoothieApplicationModule;

public class MvpApp extends Application {

    private Scope scope;

    @Override
    public void onCreate() {
        super.onCreate();

        scope = Toothpick.openScope(this);
        scope.installModules(new SmoothieApplicationModule(this));
        Toothpick.inject(this, scope);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // for test purpose only
        Toothpick.closeScope(scope);
    }
}
