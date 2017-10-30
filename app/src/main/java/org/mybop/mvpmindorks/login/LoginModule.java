package org.mybop.mvpmindorks.login;

import org.mybop.mvpmindorks.model.UserManager;

import toothpick.config.Module;

public class LoginModule extends Module {

    public LoginModule(LoginActivity activity) {
        bind(LoginContract.View.class).toInstance(activity);
        bind(LoginContract.Model.class).to(UserManager.class);
        bind(LoginContract.Presenter.class).to(LoginPresenter.class);
    }
}
