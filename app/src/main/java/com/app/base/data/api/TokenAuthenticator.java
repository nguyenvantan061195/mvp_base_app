package com.app.base.data.api;

import android.support.annotation.Nullable;

import com.app.base.data.response.OAuth2Response;
import com.app.base.global.AppPreferences;
import com.app.base.global.ServerPath;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 4:13 PM
 */
public class TokenAuthenticator implements Authenticator {
    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        boolean refreshResult = refreshToken(AppPreferences.getInstance().getOAuth2Info().getRefreshToken().getRefreshTokenValue());
        if (refreshResult) {
            String accessToken = AppPreferences.getInstance().getOAuth2Info().getAccessToken();
            return response.request().newBuilder().header("Authorization", accessToken).build();
        } else {
            return null;
        }
    }

    /*REFRESH TOKEN*/
    private boolean refreshToken(String refreshToken)
            throws IOException {
        URL refreshUrl = new URL(ServerPath.SERVER_PATH_OAUTH);
        HttpsURLConnection urlConnection = (HttpsURLConnection) refreshUrl.openConnection();
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
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        urlConnection.setDoInput(true);
        urlConnection.setConnectTimeout(ServerPath.OAUTH_TIMED_OUT);
        urlConnection.setReadTimeout(ServerPath.OAUTH_TIMED_OUT);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setUseCaches(false);
        String urlParameters = "grant_type="
                + ServerPath.GRANT_TYPE
                + "&client_id="
                + ServerPath.OAUTH_CLIENT_ID
                + "&client_secret="
                + ServerPath.OAUTH_CLIENT_SECRET
                + "&refresh_token="
                + refreshToken;

        urlConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = urlConnection.getResponseCode();
        /*HANDLE RECEIVE TOKEN DATA*/
        if (responseCode == 200) {
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            urlConnection.getInputStream().close();
            Gson gson = new Gson();
            OAuth2Response oAuth2Response =
                    gson.fromJson(response.toString(), OAuth2Response.class);
            AppPreferences.getInstance().saveOAuth2Info(oAuth2Response);
            return true;
        } else {
            return false;
        }
    }
}
