package com.app.base.global;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.telephony.TelephonyManager;

import com.app.base.BuildConfig;
import com.app.base.R;
import com.app.base.common.base.BaseActivity;
import com.app.base.common.logging.Logger;
import com.app.base.common.util.NetworkUtil;
import com.app.base.common.util.PermissionUtil;
import com.app.base.common.util.Preconditions;
import com.app.base.common.util.StringUtil;
import com.app.base.common.util.ToastUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:34 AM
 */
public class CoreApplication extends MultiDexApplication {
    private static volatile CoreApplication instance = null;
    private String deviceIMEI = Constant.STR_BLANK;
    private String serialSIM = Constant.STR_BLANK;
    private NetworkUtil mNetworkManager;
    public static Logger applicationLogger;
    public boolean isDebugMode = true;

    private static synchronized void setInstance(CoreApplication baseApplication) {
        instance = baseApplication;
    }

    public static CoreApplication getInstance() {
        return Preconditions.checkNotNull(
                instance, "CoreApplication -> onCreate(): Must call: setInstance(this)");
    }

    @Override
    public synchronized void onCreate() {
        super.onCreate();
        setInstance(this);
        MultiDex.install(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
        if (BuildConfig.DEBUG) {
            isDebugMode = true;
        }
        initNetworkManager();
        initLeakCanary();
        initLogger();
    }

    private void initLogger() {
        if (applicationLogger == null) {
            applicationLogger = new Logger();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mNetworkManager.disable(this);
        if (instance != null) instance = null;
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public Context getAppContext() {
        return instance;
    }

    private void initNetworkManager() {
        if (mNetworkManager == null) mNetworkManager = new NetworkUtil(this);
        mNetworkManager.enable(this);
    }
    public NetworkUtil getNetworkManager() {
        return mNetworkManager;
    }
    public String getDeviceIMEI(BaseActivity activity) {
        if (PermissionUtil.checkPermission(activity, Manifest.permission.READ_PHONE_STATE)) {
            if (StringUtil.isNullOrEmpty(deviceIMEI)) {
                TelephonyManager telephonyManager = (TelephonyManager) instance.getSystemService(Context.TELEPHONY_SERVICE);
                if (telephonyManager != null) {
                    deviceIMEI = telephonyManager.getDeviceId();
                }
                if (StringUtil.isNullOrEmpty(deviceIMEI)) {
                    deviceIMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            }
            if (StringUtil.isNullOrEmpty(deviceIMEI)) {
                deviceIMEI = "deviceIMEI";
            }
        } else {
            ToastUtil.showToastMessage(activity, StringUtil.getString(R.string.TEXT_MISSING_READ_PHONE_STATE_PERMISSION));
        }
        return deviceIMEI;
    }
}
