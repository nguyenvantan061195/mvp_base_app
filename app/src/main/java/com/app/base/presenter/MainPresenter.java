package com.app.base.presenter;

import com.app.base.common.base.BasePresenter;
import com.app.base.common.util.NetworkUtil;
import com.app.base.view.MainView;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 6:16 PM
 */
public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(NetworkUtil networkManager) {
        super(networkManager);
    }

    @Override
    public void refreshData(boolean type) {

    }
}
