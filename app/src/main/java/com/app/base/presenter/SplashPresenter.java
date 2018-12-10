package com.app.base.presenter;

import com.app.base.common.base.BasePresenter;
import com.app.base.common.util.NetworkUtil;
import com.app.base.view.SplashView;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:07 PM
 */
public class SplashPresenter extends BasePresenter<SplashView> {
    public SplashPresenter(NetworkUtil networkManager) {
        super(networkManager);
    }

    @Override
    public void refreshData(boolean type) {

    }
}
