package com.app.base.common.base;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 4:06 PM
 */
public abstract class BaseMapper<T1, T2> {
    public List<T1> map1(List<T2> o2List) {
        if (o2List == null) return null;

        List<T1> o1List = new ArrayList<>();
        for (T2 o2 : o2List) {
            o1List.add(map1(o2));
        }

        return o1List;
    }

    abstract T1 map1(T2 o2);

    public List<T2> map2(List<T1> o1List) {
        if (o1List == null) return null;

        List<T2> o2List = new ArrayList<>();
        for (T1 o1 : o1List) {
            o2List.add(map2(o1));
        }

        return o2List;
    }

    abstract T2 map2(T1 o1);
}
