package org.mybop.mvpmindorks.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.main.MainActivity;

import javax.inject.Inject;

import toothpick.config.Module;

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
    @Inject
    LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @Override
    public Module getMvpModule() {
        return new LoginModule(this);
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

