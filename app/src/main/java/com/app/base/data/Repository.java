package com.app.base.data;

import com.app.base.data.api.RetrofitClient;
import com.app.base.data.api.ServiceApi;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:32 AM
 */
public class Repository {
    private ServiceApi mApi;

    public Repository() {
        mApi = RetrofitClient.getApi();
    }

    /* Login */
    public Flowable<ResponseBody> requestLogin(HashMap<String, Object> body) {
        return mApi.requestLogin(body).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }
}
