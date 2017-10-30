package org.mybop.mvpmindorks.main;

import org.mybop.mvpmindorks.model.UserManager;

import toothpick.config.Module;

public class MainModule extends Module {

    public MainModule(MainActivity activity) {
        bind(MainContract.View.class).toInstance(activity);
        bind(MainContract.Model.class).to(UserManager.class);
        bind(MainContract.Presenter.class).to(MainPresenter.class);
    }
}
