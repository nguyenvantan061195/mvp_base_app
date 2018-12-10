package com.app.base.data.api;

import com.app.base.global.CoreApplication;
import com.app.base.global.ServerPath;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 5:21 PM
 */
public class RetrofitClient {
    private RetrofitClient() {
    }

    private static volatile OkHttpClient sOkHttpClient = null;
    private static volatile ServiceApi sServiceApi = null;

    public static ServiceApi getApi() {
        if (sServiceApi == null) {
            sServiceApi = createApi();
        }

        return sServiceApi;
    }

    private static ServiceApi createApi() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        RxJava2CallAdapterFactory callAdapter = RxJava2CallAdapterFactory.create();
        OkHttpClient httpClient = createHttpClient();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(ServerPath.SERVER_PATH);
        retrofitBuilder.client(httpClient);
        retrofitBuilder.addConverterFactory(ApiConverterFactory.create(gson));
        retrofitBuilder.addCallAdapterFactory(callAdapter);

        return retrofitBuilder.build().create(ServiceApi.class);
    }

    private static OkHttpClient createHttpClient() {
        if (sOkHttpClient != null) return sOkHttpClient;
        //Chi thuc hien request 1 lan trong cung 1 thoi diem
        //Khi request nay hoan thanh thi request sau moi duoc thuc hien
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new RequestInterceptor());
        httpClient.addInterceptor(new ResponseInterceptor());
        // refresh token with OAuth2
        httpClient.dispatcher(dispatcher);
        httpClient.authenticator(new TokenAuthenticator());

        if (CoreApplication.getInstance().isDebugMode) {
            HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor();
            interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptorBody);
        }

        httpClient.connectTimeout(ServerPath.HTTP_CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        httpClient.readTimeout(ServerPath.HTTP_READ_TIME_OUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(ServerPath.HTTP_WRITE_TIME_OUT, TimeUnit.SECONDS);

        // Create a trust manager that does not validate certificate chains
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[0];
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            httpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            sOkHttpClient = httpClient.build();
            return sOkHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
