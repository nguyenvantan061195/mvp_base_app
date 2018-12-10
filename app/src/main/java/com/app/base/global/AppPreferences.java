package com.app.base.global;

import android.content.Context;
import android.util.Base64;

import com.app.base.common.secure.SecurePreferences;
import com.app.base.data.response.OAuth2Response;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:46 PM
 */
public class AppPreferences {
    private static final String FILE_NAME = "base_app.xml";

    private static AppPreferences sInstance;

    private SecurePreferences mSecurePreferences;

    public static AppPreferences getInstance() {
        if (sInstance == null) {
            sInstance = new AppPreferences();
        }

        return sInstance;
    }

    private AppPreferences() {
        Context context = CoreApplication.getInstance().getApplicationContext();
        String basicAuth = String.format("%s:%s",
                AppConfig.SECURE_BASIC_USERNAME, AppConfig.SECURE_BASIC_PASSWORD);
        basicAuth = String.format("Basic %s",
                Base64.encodeToString(basicAuth.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP));
        mSecurePreferences = new SecurePreferences(context, basicAuth, FILE_NAME);
    }

    public void clear() {
        mSecurePreferences.edit().clear().apply();
    }

    public void setCookie(List<String> cookies) {
        if (!cookies.isEmpty()) {
            Set<String> cookieSet = new HashSet<>(cookies);
            mSecurePreferences.edit().putStringSet(AppConfig.APP_COOKIE, cookieSet).apply();
        }
    }

    public Set<String> getCookie() {
        return mSecurePreferences.getStringSet(AppConfig.APP_COOKIE, new HashSet<>());
    }

    public void clearCookie() {
        mSecurePreferences.edit().remove(AppConfig.APP_COOKIE).apply();
    }

    public void saveOAuth2Info(OAuth2Response oAuth2Response) {
        String json = oAuth2Response == null ? null : new Gson().toJson(oAuth2Response);
        mSecurePreferences.edit().putString(AppConfig.OAUTH2_INFO, json).apply();
    }

    public OAuth2Response getOAuth2Info() {
        String json = mSecurePreferences.getString(AppConfig.OAUTH2_INFO, null);
        return json == null ? null : new Gson().fromJson(json, OAuth2Response.class);
    }
}
