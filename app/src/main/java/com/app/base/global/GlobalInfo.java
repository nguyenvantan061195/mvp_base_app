package com.app.base.global;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 4:59 PM
 */
public class GlobalInfo {
    private static GlobalInfo instance;
    private GlobalInfo() {
    }

    public static interface IDataValidate<T> {
        boolean valid(T ob);
    }


    public static GlobalInfo getInstance() {
        if (instance == null) {
            instance = new GlobalInfo();
        }
        return instance;
    }
}
