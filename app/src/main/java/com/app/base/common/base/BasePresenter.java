package com.app.base.common.base;

import com.app.base.common.util.Preconditions;
import com.app.base.common.util.NetworkUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public abstract class BasePresenter<V extends BaseView> {
    private NetworkUtil mNetworkManager;
    private CompositeDisposable mDisposable;
    private V mView;

    public BasePresenter(NetworkUtil networkManager) {
        mNetworkManager = networkManager;
        mDisposable = new CompositeDisposable();
    }

    void attachView(V view) {
        Preconditions.checkNotNull(view, "view is null");
        mView = view;
        onViewAttached();
    }

    private void onViewAttached() {
    }

    void detachView() {
        if (mNetworkManager != null) {
            mNetworkManager.remove(toString());
        }
        mDisposable.clear();
        onViewDetached();
        mView = null;
    }

    private void onViewDetached() {
    }

    void resume() {
        mNetworkManager.add(toString(), this::refreshData);
    }

    void pause() {
        mNetworkManager.remove(toString());
    }

    public void destroy() {
        onDestroyed();
    }

    private void onDestroyed() {

    }

    /*@param type is WIFI (false) or LTE (true) */
    public abstract void refreshData(boolean type);

    protected void addDisposable(Disposable disposable) {
        if (mDisposable != null) mDisposable.add(disposable);
    }

    public void clearDisposable() {
        if (mDisposable != null) mDisposable.clear();
    }
}
