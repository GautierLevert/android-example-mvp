package org.mybop.mvpmindorks.login;

import android.content.Intent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.mybop.mvpmindorks.BaseActivity;
import org.mybop.mvpmindorks.R;
import org.mybop.mvpmindorks.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import toothpick.config.Module;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    // UI references.
    @BindView(R.id.email)
    EditText mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;
    @BindView(R.id.email_sign_in_button)
    Button mEmailSignInButton;

    // presenter
    @Inject
    LoginContract.Presenter loginPresenter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onViewBound() {
        super.onViewBound();

        addSubscription(RxTextView.editorActions(mPasswordView, (id) -> id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL)
                .subscribe(n -> attemptLogin()));

        addSubscription(RxView.clicks(mEmailSignInButton)
                .subscribe(n -> attemptLogin()));
    }

    @Override
    public Module getMvpModule() {
        return new LoginModule(this);
    }

    private void attemptLogin() {
        loginPresenter.attemptLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
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

