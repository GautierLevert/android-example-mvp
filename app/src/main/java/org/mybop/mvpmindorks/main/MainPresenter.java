package org.mybop.mvpmindorks.main;

import android.support.annotation.NonNull;

import org.mybop.mvpmindorks.BasePresenter;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model> implements MainContract.Presenter {

    public MainPresenter(@NonNull MainContract.View view, @NonNull MainContract.Model dataManager) {
        super(view, dataManager);
    }

    @Override
    public Single<String> getEmail() {
        return getDataManager()
                .getEmail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void logOut() {
        getDataManager().clear()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getView().openSplashActivity());
    }
}
