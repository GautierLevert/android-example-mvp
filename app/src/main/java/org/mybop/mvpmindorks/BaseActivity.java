package org.mybop.mvpmindorks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;
import toothpick.smoothie.module.SmoothieSupportActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    /**
     * Toothpick scope
     */
    private Scope scope;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new SmoothieSupportActivityModule(this));
        scope.installModules(getMvpModule());
        scope.installModules(getActivityModules());
        Toothpick.inject(this, scope);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toothpick.closeScope(scope);
    }

    /**
     * @return the module used for the contact binding.
     */
    public abstract Module getMvpModule();

    /**
     * Overrides this method to ass complementary module for your Activity
     *
     * @return an array of modules for this Activity
     */
    protected Module[] getActivityModules() {
        return new Module[0];
    }
}
