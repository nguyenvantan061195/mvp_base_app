package com.app.base.data.response;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 4:32 PM
 */
public class OAuth2Response {
    @SerializedName("value")
    @Expose
    private String accessToken;

    @SerializedName("expiration")
    @Expose
    private long expiration;

    @SerializedName("tokenType")
    @Expose
    private String tokenType;

    @SerializedName("refreshToken")
    @Expose
    private RefreshToken refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public class RefreshToken {
        @SerializedName("value")
        @Expose
        private String refreshTokenValue;

        @SerializedName("expiration")
        @Expose
        private long expiration;

        public String getRefreshTokenValue() {
            return refreshTokenValue;
        }

        public void setRefreshTokenValue(String refreshTokenValue) {
            this.refreshTokenValue = refreshTokenValue;
        }

        public long getExpiration() {
            return expiration;
        }

        public void setExpiration(long expiration) {
            this.expiration = expiration;
        }

        @NonNull
        @Override
        public String toString() {
            return refreshTokenValue;
        }
    }
}
