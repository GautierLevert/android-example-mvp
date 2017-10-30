package org.mybop.mvpmindorks.splash;

import org.mybop.mvpmindorks.model.UserManager;

import toothpick.config.Module;

public class SplashModule extends Module {

    public SplashModule(SplashActivity splashActivity) {
        bind(SplashContract.View.class).toInstance(splashActivity);
        bind(SplashContract.Model.class).to(UserManager.class);
        bind(SplashContract.Presenter.class).to(SplashPresenter.class);
    }
}
