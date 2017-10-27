package org.mybop.mvpmindorks.splash;

import org.mybop.mvpmindorks.MvpModel;
import org.mybop.mvpmindorks.MvpPresenter;
import org.mybop.mvpmindorks.MvpView;

public interface SplashContract {
    interface View extends MvpView {
        void openMainActivity();

        void openLoginActivity();
    }

    interface Presenter extends MvpPresenter<SplashContract.View, SplashContract.Model> {
        void decideNextActivity();
    }

    interface Model extends MvpModel {
        boolean getLoggedInMode();
    }
}
