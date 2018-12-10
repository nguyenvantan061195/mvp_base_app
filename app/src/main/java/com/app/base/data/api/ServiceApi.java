package com.app.base.data.api;

import java.util.HashMap;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 5:20 PM
 */
public interface ServiceApi {
    /**
     * This endpoint returns information
     */
    @POST("authController/login")
    Flowable<ResponseBody> requestLogin(@Body HashMap<String, Object> body);
}
