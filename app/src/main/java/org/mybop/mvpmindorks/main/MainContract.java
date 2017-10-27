package org.mybop.mvpmindorks.main;

import org.mybop.mvpmindorks.MvpModel;
import org.mybop.mvpmindorks.MvpPresenter;
import org.mybop.mvpmindorks.MvpView;

public interface MainContract {
    interface View extends MvpView {
        void openSplashActivity();
    }

    interface Model extends MvpModel {

        void clear();

        String getEmail();
    }

    interface Presenter extends MvpPresenter<MainContract.View, MainContract.Model> {
        String getEmail();

        void logOut();
    }
}
