package org.mybop.mvpmindorks.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.splash.SplashActivity;

import javax.inject.Inject;

import toothpick.config.Module;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Logged in as " + mainPresenter.getEmail());

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.logOut();
            }
        });
    }

    @Override
    public Module getMvpModule() {
        return new MainModule(this);
    }

    @Override
    public void openSplashActivity() {
        startActivity(new Intent(this, SplashActivity.class));
    }
}
