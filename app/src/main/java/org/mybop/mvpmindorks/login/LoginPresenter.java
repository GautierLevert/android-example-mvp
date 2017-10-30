package org.mybop.mvpmindorks.login;

import android.support.annotation.NonNull;

import org.mybop.mvpmindorks.BasePresenter;
import org.mybop.mvpmindorks.CommonUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    @Inject
    LoginPresenter(@NonNull LoginContract.View view, @NonNull LoginContract.Model dataManager) {
        super(view, dataManager);
    }

    @Override
    public void attemptLogin(final String email, final String password) {
        Completable.fromAction(() -> {
            if (!CommonUtils.isEmailValid(email) || password == null || password.isEmpty()) {
                throw new IllegalArgumentException("email or password invalid");
            }
        })
                .andThen(getDataManager().saveEmail(email))
                .andThen(getDataManager().setLoggedIn())
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable ->
                        getView().showLoginInProgress())
                .subscribe(() ->
                                getView().openMainActivity(),
                        throwable ->
                                getView().showLoginFailed());
    }
}
