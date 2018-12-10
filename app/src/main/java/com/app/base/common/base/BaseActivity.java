package com.app.base.common.base;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.app.base.common.util.DialogUtil;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    // cho phep hien thi dialog khi activity chua onStop
    public boolean isAllowShowFragmentDialog = true;
    protected P mPresenter;
    protected View mLoadingView = null;

    @CallSuper
    @Override
    protected void onDestroy() {
        Log.i(this.getClass().getSimpleName(), "onDestroy BEGIN");
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @CallSuper
    @Override
    protected void onStop() {
        this.isAllowShowFragmentDialog = false;
        super.onStop();
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(view);
        if (View.NO_ID != getLoadingViewId()) {
            mLoadingView = findViewById(getLoadingViewId());
        }
        mPresenter = getPresenter();
        if (mPresenter != null) mPresenter.attachView(this);
        initView();
    }

    public abstract void initView();

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    @Override
    protected void onPause() {
        Log.i(this.getClass().getSimpleName(), "onPause BEGIN");
        if (mPresenter != null) mPresenter.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        this.isAllowShowFragmentDialog = true;
        Log.i(this.getClass().getSimpleName(), "onResume BEGIN");
        super.onResume();
        if (mPresenter != null) mPresenter.resume();
    }

    @Override
    public void showError(Throwable err) {
    }


    @Override
    public void showLoading(byte loadingType) {
        if (loadingType == LOADING_ON) {
            DialogUtil.showLoadingDialog(this, false);
        } else {
            DialogUtil.closeCurrentDialog(this);
        }
    }

    @Override
    public void hideKeyboard() {
        View view = getCurrentFocus();
        IBinder windowToken = view != null ? view.getWindowToken() : null;
        if (windowToken != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(windowToken, 0);
                view.clearFocus();
                view.setFocusableInTouchMode(true);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Replace fragment in the activity container
     *
     * @param containerId    frame layout container Id
     * @param fragment       fragment to replace
     * @param addToBackStack whether to add or not to back stack
     */
    protected void replaceFragment(
            @IdRes int containerId, @NonNull Fragment fragment, boolean addToBackStack) {

        String fragmentTag = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, fragmentTag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }

    /**
     * Replace fragment in the activity container
     * (not add fragment to back stack)
     *
     * @param containerId frame layout container Id
     * @param fragment    fragment to replace
     */
    protected void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment) {
        replaceFragment(containerId, fragment, false);
    }

    @IdRes
    public int getLoadingViewId() {
        return View.NO_ID;
    }
}
