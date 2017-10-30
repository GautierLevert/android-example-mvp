package org.mybop.mvpmindorks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;
import toothpick.smoothie.module.SmoothieSupportActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    /**
     * Toothpick scope
     */
    private Scope scope;

    /**
     * Butterknife unbinder
     */
    private Unbinder unbinder;

    /**
     * RxJava subscriptions to dispose on activity destroyed to avoid memory leaks
     */
    private CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new SmoothieSupportActivityModule(this));
        scope.installModules(getMvpModule());
        scope.installModules(getActivityModules());
        Toothpick.inject(this, scope);

        onInjectionDone();

        setContentView();

        unbinder = ButterKnife.bind(this);

        onViewBound();
    }

    protected void onViewBound() {
    }

    protected abstract void setContentView();

    protected void onInjectionDone() {
    }

    protected void addSubscription(@NonNull Disposable disposable) {
        subscriptions.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.dispose();
        Toothpick.closeScope(scope);
        unbinder.unbind();
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
