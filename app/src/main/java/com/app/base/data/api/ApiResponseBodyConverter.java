package com.app.base.data.api;

import com.app.base.common.exception.ErrorException;
import com.app.base.data.response.BaseResponse;
import com.app.base.global.ErrorConstant;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 5:27 PM
 */
public class ApiResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    ApiResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);

            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }

            if (result instanceof BaseResponse) {
                BaseResponse item = (BaseResponse) result;
                int code = item.getErrorCode();
                if (code == ErrorConstant.SUCCESS_CODE) {
                    return result;
                }

                throw new ErrorException(code, item.getErrorMessage());
            } else {
                return result;
            }

        } finally {
            value.close();
        }
    }
}
