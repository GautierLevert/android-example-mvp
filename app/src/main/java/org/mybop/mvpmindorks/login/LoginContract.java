package org.mybop.mvpmindorks.login;

import org.mybop.mvpmindorks.MvpModel;
import org.mybop.mvpmindorks.MvpPresenter;
import org.mybop.mvpmindorks.MvpView;

public interface LoginContract {
    interface View extends MvpView {

        void showLoginInProgress();

        void showLoginFailed();

        void openMainActivity();

    }

    interface Presenter extends MvpPresenter<LoginContract.View, LoginContract.Model> {

        void attemptLogin(String email, String password);
    }

    interface Model extends MvpModel {

        void saveEmail(String email);

        void setLoggedIn();
    }
}
