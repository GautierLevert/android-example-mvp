package org.mybop.mvpmindorks.main;

import org.mybop.mvpmindorks.MvpModel;
import org.mybop.mvpmindorks.MvpPresenter;
import org.mybop.mvpmindorks.MvpView;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MainContract {
    interface View extends MvpView {
        void openSplashActivity();
    }

    interface Model extends MvpModel {

        Completable clear();

        Single<String> getEmail();
    }

    interface Presenter extends MvpPresenter<MainContract.View, MainContract.Model> {
        Single<String> getEmail();

        void logOut();
    }
}
