package org.mybop.mvpmindorks.model;

import org.mybop.mvpmindorks.login.LoginContract;
import org.mybop.mvpmindorks.main.MainContract;
import org.mybop.mvpmindorks.splash.SplashContract;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserManager implements SplashContract.Model, LoginContract.Model, MainContract.Model {

    SharedPrefsHelper sharedPrefsHelper;

    @Inject
    UserManager(SharedPrefsHelper sharedPrefsHelper) {
        this.sharedPrefsHelper = sharedPrefsHelper;
    }

    @Override
    public void clear() {
        sharedPrefsHelper.clear();
    }

    @Override
    public void saveEmail(String email) {
        sharedPrefsHelper.putEmail(email);
    }

    @Override
    public String getEmail() {
        return sharedPrefsHelper.getEmail();
    }

    @Override
    public void setLoggedIn() {
        sharedPrefsHelper.setLoggedInMode();
    }

    @Override
    public boolean getLoggedInMode() {
        return sharedPrefsHelper.getLoggedInMode();
    }
}
