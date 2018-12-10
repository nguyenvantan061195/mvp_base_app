package com.app.base.data.api;

import com.app.base.global.AppPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 5:18 PM
 */
public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        AppPreferences.getInstance().setCookie(originalResponse.headers("Set-Cookie"));
        return originalResponse;
    }
}
