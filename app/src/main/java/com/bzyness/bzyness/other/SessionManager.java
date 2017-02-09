package com.bzyness.bzyness.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * This class maintains data across the app using the SharedPreferences.
 * We store a boolean flag isLoggedIn in shared preferences to check the login status.
 */

public class SessionManager {
    // Logcat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "BzynessLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // Commit Changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setAccessToken(String accessToken) {
        editor.putString("access_token",accessToken);
        editor.commit();
        if(accessToken!="")
            setIsLogin(true);
    }

    public String getAccessToken() {
        return pref.getString("access_token","");
    }

    public void setExpiresIn(String expiresIn)
    {
        editor.putString("expires_in",expiresIn);
        editor.commit();
    }

    public String getExpiresIn() {
        return pref.getString("expires_in","");
    }

    public void setIsLogin(boolean b) {
        editor.putBoolean("is_login", b);
        editor.commit();
    }

    public boolean getIsLogin() {
        return pref.getBoolean("is_login",false);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
