package com.app.base.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.base.R;
import com.app.base.common.base.BaseActivity;
import com.app.base.common.util.DialogUtil;
import com.app.base.global.CoreApplication;
import com.app.base.presenter.MainPresenter;
import com.app.base.view.MainView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        DialogUtil.showLoadingDialog(this, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(CoreApplication.getInstance().getNetworkManager());
    }
}
