package com.app.base.common.base;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public abstract class RxFlowableDisposable<E> extends DisposableSubscriber<E> {
    private final BaseView mBaseView;
    private byte mLoadingType;

    public RxFlowableDisposable(BaseView baseView, byte loadingType) {
        mBaseView = baseView;
        mLoadingType = loadingType;
    }

    @Override
    protected void onStart() {
        mBaseView.showLoading(mLoadingType);
        super.onStart();
    }

    @Override
    public void onError(Throwable t) {
        mBaseView.showLoading(BaseView.LOADING_OFF);
        mBaseView.showError(t);
    }

    @Override
    public void onNext(E e) {
        onNext(e, mLoadingType);
    }

    abstract void onNext(E e, byte loadingType);

    @Override
    public void onComplete() {
        mBaseView.showLoading(BaseView.LOADING_OFF);
    }
}
