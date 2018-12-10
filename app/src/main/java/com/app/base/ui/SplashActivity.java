package com.app.base.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.app.base.R;
import com.app.base.common.base.BaseActivity;
import com.app.base.common.util.StringUtil;
import com.app.base.global.Constant;
import com.app.base.global.CoreApplication;
import com.app.base.presenter.SplashPresenter;
import com.app.base.view.SplashView;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {
    private AppCompatTextView tvIntro;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> MainActivity.start(SplashActivity.this), Constant.SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void initView() {
        tvIntro = findViewById(R.id.tvIntro);
        tvIntro.setText(StringUtil.getString(R.string.app_name));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenter getPresenter() {
        return new SplashPresenter(CoreApplication.getInstance().getNetworkManager());
    }

    @Override
    public void initialize() {
        // Handle start app here
    }
}
