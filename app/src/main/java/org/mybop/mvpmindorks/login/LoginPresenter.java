package org.mybop.mvpmindorks.login;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.mybop.mvpmindorks.BasePresenter;
import org.mybop.mvpmindorks.CommonUtils;

import javax.inject.Inject;

/**
 * Created by gautier on 27/10/2017.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    @Inject
    LoginPresenter(@NonNull LoginContract.View view, @NonNull LoginContract.Model dataManager) {
        super(view, dataManager);
    }

    @Override
    public void attemptLogin(final String email, final String password) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                getView().showLoginInProgress();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (!CommonUtils.isEmailValid(email) || password == null || password.isEmpty()) {
                    getView().showLoginFailed();
                    return;
                }

                getDataManager().saveEmail(email);
                getDataManager().setLoggedIn();

                getView().openMainActivity();
            }
        }.execute();
    }
}
