package org.mybop.mvpmindorks.splash;

import android.support.annotation.NonNull;

import org.mybop.mvpmindorks.BasePresenter;

public class SplashPresenter extends BasePresenter<SplashContract.View, SplashContract.Model> implements SplashContract.Presenter {

    public SplashPresenter(@NonNull SplashContract.View view, @NonNull SplashContract.Model dataManager) {
        super(view, dataManager);
    }

    @Override
    public void decideNextActivity() {
        if (getDataManager().getLoggedInMode()) {
            getView().openMainActivity();
        } else {
            getView().openLoginActivity();
        }
    }
}
