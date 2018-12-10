package com.app.base.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P mPresenter;
    protected BaseActivity<? extends BasePresenter> mParentActivity;

    @CallSuper
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @CallSuper
    @Override
    public void onDetach() {
        mParentActivity = null;
        super.onDetach();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public abstract void initView(View v);

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mPresenter = getPresenter();
        if (mPresenter != null) mPresenter.attachView(this);
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    @IdRes
    public int getLoadingViewId() {
        return View.NO_ID;
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.resume();
    }

    @CallSuper
    @Override
    public void onPause() {
        if (mPresenter != null) mPresenter.pause();
        super.onPause();
    }

    @Override
    public void showError(Throwable err) {
        mParentActivity.showError(err);
    }

    @Override
    public void showLoading(byte loadingType) {
        mParentActivity.showLoading(loadingType);
    }

    @Override
    public void hideKeyboard() {
        mParentActivity.hideKeyboard();
    }

    public void refresh() {
    }
}
