package org.mybop.mvpmindorks.splash;

import android.content.Intent;
import android.os.Bundle;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.MvpApp;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.login.LoginActivity;
import org.mybop.mvpmindorks.main.MainActivity;
import org.mybop.mvpmindorks.model.UserManager;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UserManager dataManager = ((MvpApp) getApplication()).getUserManager();

        splashPresenter = new SplashPresenter(this, dataManager);
        
        splashPresenter.decideNextActivity();

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
