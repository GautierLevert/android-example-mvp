package org.mybop.mvpmindorks.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.splash.SplashActivity;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import toothpick.config.Module;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mainPresenter;

    private TextView textView;

    private CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        subscriptions.add(
                mainPresenter.getEmail()
                        .subscribe(email -> textView.setText("Logged in as " + email))
        );

        subscriptions.add(
                RxView.clicks(findViewById(R.id.button))
                        .subscribe(o -> mainPresenter.logOut())
        );
    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        subscriptions.dispose();
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
