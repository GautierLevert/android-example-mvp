package org.mybop.mvpmindorks.splash;

import android.content.Intent;
import android.os.Bundle;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.login.LoginActivity;
import org.mybop.mvpmindorks.main.MainActivity;

import javax.inject.Inject;

import toothpick.config.Module;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashPresenter.decideNextActivity();
    }

    @Override
    public Module getMvpModule() {
        return new SplashModule(this);
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
