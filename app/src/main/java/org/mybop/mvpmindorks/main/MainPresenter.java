package org.mybop.mvpmindorks.main;

import android.support.annotation.NonNull;

import org.mybop.mvpmindorks.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model> implements MainContract.Presenter {

    public MainPresenter(@NonNull MainContract.View view, @NonNull MainContract.Model dataManager) {
        super(view, dataManager);
    }

    @Override
    public String getEmail() {
        return getDataManager().getEmail();
    }

    @Override
    public void logOut() {
        getDataManager().clear();
        getView().openSplashActivity();
    }
}
