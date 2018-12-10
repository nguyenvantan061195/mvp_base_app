package com.app.base.data.api;

import com.app.base.common.exception.ErrorException;
import com.app.base.global.AppPreferences;
import com.app.base.global.CoreApplication;
import com.app.base.global.ErrorConstant;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:31 AM
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!CoreApplication.getInstance().getNetworkManager().isNetworkAvailable) {
            throw new ErrorException(ErrorConstant.NETWORK_NOT_AVAILABLE, "Network is not available");
        }

        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        builder.addHeader("Content-Type", "application/json;charset=UTF-8");
        builder.addHeader("Authorization", getOAuth2());

        Set<String> cookies = AppPreferences.getInstance().getCookie();
        for (String cookie : cookies) {
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }

    private String getOAuth2() {
        return AppPreferences.getInstance().getOAuth2Info().getAccessToken();
    }
}
