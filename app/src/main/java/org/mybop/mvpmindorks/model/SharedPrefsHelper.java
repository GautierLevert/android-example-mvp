package org.mybop.mvpmindorks.model;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefsHelper {

    public static final String EMAIL = "EMAIL";

    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    private SharedPreferences mSharedPreferences;

    @Inject
    SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    public void putEmail(String email) {
        mSharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public String getEmail() {
        return mSharedPreferences.getString(EMAIL, null);
    }

    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public void setLoggedIn() {
        mSharedPreferences.edit().putBoolean(IS_LOGGED_IN, true).apply();
    }
}
