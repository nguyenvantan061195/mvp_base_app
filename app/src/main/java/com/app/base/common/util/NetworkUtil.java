package com.app.base.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:37 AM
 */
public class NetworkUtil extends ConnectivityManager.NetworkCallback {
    public boolean isNetworkAvailable = false;
    private final NetworkRequest networkRequest;
    private ConnectivityManager connectivityManager;
    private final Map<String, NetworkListener> mListenerMap = new HashMap<>();

    public NetworkUtil(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();
    }

    public void add(String tag, NetworkListener listener) {
        mListenerMap.put(tag, listener);
    }

    public void remove(String tag) {
        mListenerMap.remove(tag);
    }

    public void enable(Context context) {
        connectivityManager.registerNetworkCallback(networkRequest, this);
    }

    public void disable(Context context) {
        connectivityManager.unregisterNetworkCallback(this);
    }

    // Likewise, you can have a disable method that simply calls ConnectivityManager#unregisterCallback(networkRequest) too.
    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        // Do what you need to do here
        if (network != null) {
            for (NetworkListener listener : mListenerMap.values()) {
                if (listener != null) {
                    listener.onNetworkAvailable(connectivityManager.isActiveNetworkMetered());
                }
            }
            isNetworkAvailable = true;
        } else {
            isNetworkAvailable = false;
        }
    }

    @Override
    public void onUnavailable() {
        super.onUnavailable();
        LogUtil.e("networkCallback", "inactive connection");
        isNetworkAvailable = false;
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        LogUtil.e("networkCallback", "losing active connection");
        isNetworkAvailable = false;
    }

    /**
     * Network availability listener
     */
    public interface NetworkListener {
        /**
         * Is triggered when network connection appears
         *
         * @param type ? LTE : WIFI
         */
        void onNetworkAvailable(boolean type);
    }
}
