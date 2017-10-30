package org.mybop.mvpmindorks.main;

import android.content.Intent;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.splash.SplashActivity;

import javax.inject.Inject;

import butterknife.BindView;
import toothpick.config.Module;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.textView)
    TextView textView;

    @Inject
    MainContract.Presenter mainPresenter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onViewBound() {
        super.onViewBound();

        addSubscription(
                mainPresenter.getEmail()
                        .subscribe(email -> textView.setText("Logged in as " + email))
        );

        addSubscription(
                RxView.clicks(findViewById(R.id.button))
                        .subscribe(o -> mainPresenter.logOut())
        );
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
