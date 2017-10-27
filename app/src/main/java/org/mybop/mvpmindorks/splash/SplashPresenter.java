package org.mybop.mvpmindorks.splash;

import android.support.annotation.NonNull;

import org.mybop.mvpmindorks.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter extends BasePresenter<SplashContract.View, SplashContract.Model> implements SplashContract.Presenter {

    public SplashPresenter(@NonNull SplashContract.View view, @NonNull SplashContract.Model dataManager) {
        super(view, dataManager);
    }

    @Override
    public void decideNextActivity() {
        getDataManager().getLoggedInMode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((logged) -> {
                    if (logged) {
                        getView().openMainActivity();
                    } else {
                        getView().openLoginActivity();
                    }
                });
    }
}
