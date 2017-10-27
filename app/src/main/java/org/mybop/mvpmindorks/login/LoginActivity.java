package org.mybop.mvpmindorks.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.MvpApp;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.main.MainActivity;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;

    // presenter
    private LoginPresenter loginPresenter;

    private CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();

        loginPresenter = new LoginPresenter(this, ((MvpApp) getApplication()).getUserManager());

        subscriptions.add(RxTextView.editorActions(mPasswordView, (id) -> id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL)
                .subscribe(n -> attemptLogin()));

        subscriptions.add(RxView.clicks(mEmailSignInButton)
                .subscribe(n -> attemptLogin()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.dispose();
    }

    private void attemptLogin() {
        loginPresenter.attemptLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    private void bindViews() {
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);
    }

    @Override
    public void showLoginInProgress() {
        mProgressView.setVisibility(View.VISIBLE);
        mEmailView.setEnabled(false);
        mPasswordView.setEnabled(false);
        mEmailSignInButton.setEnabled(false);
        mLoginFormView.setVisibility(View.GONE);
    }

    @Override
    public void showLoginFailed() {
        mProgressView.setVisibility(View.GONE);
        mEmailView.setEnabled(true);
        mPasswordView.setEnabled(true);
        mEmailSignInButton.setEnabled(true);
        mLoginFormView.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}

